build:
	go build -o app main.go

build_linux:
	GOOS=linux go build -o app main.go

build_linux_amd64:
	GOOS=linux GOARCH=amd64 go build -o app main.go

test:
	go test -v ./...

coverage:
	go test ./... -coverprofile=coverage.out
	go tool cover -func coverage.out

start_local:
	func start
