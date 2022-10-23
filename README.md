# C195 Performance Assessment

## Local Setup

The following are the steps used to create a local project that replicates the lab environment.

### Requirements
- Windows 10
- IntelliJ IDEA 2021.1.3 (Community Edition) ([Download](https://download.jetbrains.com/idea/ideaIC-2021.1.3.exe))
- JDK 17.0.1 ([Download](https://download.oracle.com/java/17/archive/jdk-17.0.1_windows-x64_bin.exe))
- JavaFX SDK 11.0.2 ([Download](https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_windows-x64_bin-sdk.zip))
    - Extract ZIP file in a known directory.
- MySQL 8.0.30 ([Download](https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.30.0.msi))
    - Custom Install
        - MySQL Server
        - MySQL Workbench

### Project Setup

1. Create a new JavaFX project in IntelliJ
    - Click "Create New Project"
    - Select "JavaFX"
    - Select the 17.0.1 Project SDK
    - Select and save the project location
2. Set the PATH_TO_FX path variable
    - File > Settings > Appearance & Behavior > Path Variable
    - Add a new variable ("+" icon)
        - Name: `PATH_TO_FX`
        - Value: Path the extracted JavaFX SDK "lib" directory
3. Add the JavaFX SDK library
    - File > Project Structure > Libraries
    - Add a new "Java" library ("+" icon)
    - Select the extracted JavaFX SDK "lib" directory
4. Update the VM options
    - Run > Edit Configurations
    - Edit the configuration for the Application > java.ClientSchedulingApp entrypoint
    - Select the "Modify Options" dropdown and click "Add VM Options"
        - `--module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics`
        - Required for reviewers to load JavaFX modules using their PATH_TO_FX variable.
5. Add the MySQL driver library
    - File > Project Structure > Libraries
    - Add a new "From Maven..." library ("+" icon)
    - Search for and select `mysql:mysql-connector-java:8.0.25`
    - Check "Download to:" with the default project library path
    - Uncheck "Transitive dependencies"
    - Confirm and download the driver

### DB Setup

1. Add MySQL to path

```
setx PATH ^%PATH^%;"C:\Program Files\MySQL\MySQL Server 8.0\bin"
```

2. Connect to MySQL as root user

```
mysql.exe -u root -p
```

1. Create database

```
create database client_schedule;
```

2. Create application user

```
create user 'sqlUser'@'localhost' identified by 'Passw0rd!';
```

3. Grant database privileges to user

```
grant all privileges on client_schedule.* to 'sqlUser'@'localhost';
```

4. Create tables

```
source data\C195_db_ddl.sql
```

5. Create data

```
source data\C195_db_dml.sql
```

6. Set server time to UTC

Edit `C:\ProgramData\Mysql\MySQL Server 8.0\my.ini`:

```
[mysqld]
default-time-zone="+00:00"
```

Restart the MySQL server.
