#!/usr/bin/env bash

docker run -it --rm -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 postgres
