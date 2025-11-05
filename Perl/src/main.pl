#!usr/bin/env perl
use HTTP::Daemon;
use HTTP::Status;
use Env;

my $port = $ENV{"FUNCTIONS_CUSTOMHANDLER_PORT"};
my $function_name = "SimpleHttpTrigger";
my $d = HTTP::Daemon->new(LocalAddr => "127.0.0.1", LocalPort => $port ) || die;
print "Please contact me at: <URL:", $d->url, ">\n";

while (my $c = $d->accept) {
    while (my $r = $c->get_request) {
        if ($r->method eq "GET" and $r->uri->path eq "/api/" . $function_name) {
            print "Perl is running on Azure Functions";
            $c->send_response( HTTP::Response->new(200, "OK", undef, "Perl is running on Azure Functions\n") );
        }
        else {
            $c->send_error(RC_FORBIDDEN)
        }
    }
    $c->close;
    undef($c);
}