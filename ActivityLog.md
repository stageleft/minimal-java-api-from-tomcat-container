# 作業記録

## 本書の目的

本プロジェクトは、 Tomcatコンテナ + Java アプリ を用いて、最小のAPIコンテナを構成することを目的とする。

本書は、上記コンテナを構成する手順を記録する。

## VS Code のインストール

https://azure.microsoft.com/ja-jp/products/visual-studio-code

### プラグイン

Remote Development by Microsoft https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.vscode-remote-extensionpack
Github Copilot bu GitHub https://marketplace.visualstudio.com/items?itemName=GitHub.copilot

## WSLのインストール

コマンドプロンプトでWSLをインストールする。デフォルト、WSL Ubuntu がインストールされる。

```cmd
> wsl --install
> wsl -l -v
  NAME      STATE           VERSION
* Ubuntu    Running         2
```

VScodeからWSLにSSHでアタッチし、バージョンを確認。
今回は、 WSL 2 における、 Ubuntu24.04 だった。

```sh
$ lsb_release -a
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 24.04.1 LTS
Release:        24.04
Codename:       noble
```

## Docker のインストール

Docker Engine を Ubuntu にインストール。 see. https://docs.docker.com/engine/install/ubuntu/

カレントユーザーを docker グループに登録、dockerの常時起動を設定。 https://docs.docker.com/engine/install/linux-postinstall/

## Tomcatコンテナのpullと内容確認

https://hub.docker.com/_/tomcat を見て考え...る前に、最小のDockerfileをVSCodeでさっくり作る。

```sh
$ mkdir build && cd $_
$ touch Dockerfile
$ cat Dockerfile
FROM tomcat:latest
```

compose.yaml を準備、とりあえず動かしてみる。

```sh
$ touch compose.yaml
$ cat compose.yaml
services:
  tomcat-java-api:
    image: tomcat-java-api:latest
    build:
      context: ..
      dockerfile: build/Dockerfile
$ docker compose build
$ docker compose up -d
```

で、入ってみたところ、それっぽくなったので、まずは成功。Dockerfileの最終行に与えたコマンドを特定しておく。

```sh
$ docker exec -it build-tomcat-java-api-1 bash
root@db618196f52e:/usr/local/tomcat# ls
bin           conf             filtered-KEYS  LICENSE  native-jni-lib  README.md      RUNNING.txt  upstream-KEYS  webapps.dist
BUILDING.txt  CONTRIBUTING.md  lib            logs     NOTICE          RELEASE-NOTES  temp         webapps        work
root@db618196f52e:/usr/local/tomcat# exit
$ docker inspect build-tomcat-java-api-1 | jq '.[].Config.Cmd'
[
  "catalina.sh",
  "run"
]
$ cat Dockerfile
FROM tomcat:latest
CMD ["catalina.sh", "run"]
$ docker compose build && docker compose up -d
```
