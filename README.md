# JSON and YAML differ

[![Actions Status](https://github.com/exicc/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/exicc/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/d8225adf066eda7c321a/maintainability)](https://codeclimate.com/github/exicc/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/d8225adf066eda7c321a/test_coverage)](https://codeclimate.com/github/exicc/java-project-71/test_coverage)

## About
This is a simple project for comparing JSON and YAML files (like Jsondiff).
There are several output formats: _stylish_ (used by default), _plain_ and _json_.

To find out the syntax of the application, you need to run it with the <u>__-h__</u> or <u>__--help__</u> flag.
***
### How the application works:
[![asciicast](https://asciinema.org/a/NPKtabY8O7Y64iPShmLmLaIyp.svg)](https://asciinema.org/a/NPKtabY8O7Y64iPShmLmLaIyp)
***
## System requirements
Java >= 20.0.1

Gradle >= 8.2.1
***
## Usage examples
### 1. Using `install` task
```sh
make install
```
```sh
./app/build/install/app/bin/app file1.json file2.json
./app/build/install/app/bin/app file1.json file2.json -f plain
./app/build/install/app/bin/app file1.json file2.json -f json
```
**or**
```sh
./app/build/install/app/bin/app file1.yaml file2.yaml
./app/build/install/app/bin/app file1.yaml file2.yaml -f plain
./app/build/install/app/bin/app file1.yaml file2.yaml -f json
```

###  2. Using `.jar` file
```sh
make build
```
```sh
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.json file2.json 
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.json file2.json -p plain
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.json file2.json -p json
```
**or**
```sh
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.yaml file2.yaml 
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.yaml file2.yaml -p plain
java -jar build/libs/app-1.0.3-SNAPSHOT.jar file1.yaml file2.yaml -p json
```

##### **Hint**: ***file1*** and ***file2*** are the files that need to be compared. Also ***.jar*** file can be moved and run from any directory you want.