## Cloud Run

## 主な使用ライブラリ
- [Spring Boot (フレームワーク)](https://spring.io/projects/spring-boot)
- [DOMA2 (O/Rマッパー)](https://doma.readthedocs.io/en/latest/)


### IntelliJの設定

- DOMA plugin のインストール
    - メニューから`File` `Settings` `Plugins` を選択
    - `doma` を入力し、`Search Results` から `Doma Support` を選択し、インストール。


### ローカル起動
- IntelliJ に、下記の環境変数を設定する：

| Name | Value                                                   |
| :--- |:--------------------------------------------------------|
| INSTANCE_CONNECTION_NAME |   |
| DB_NAME | <データベース名>                                               |
| DB_USER   | <ユーザ名>                                                  |
| DB_PASS | <パスワード>                                                 |
| PROJECT_ID |                                     |
| GOOGLE_APPLICATION_CREDENTIALS | <サービスアカウントキーファイル>                                       |
- IntelliJ の機能を利用して起動する。


## API仕様
### OpenAPI 3 & Swagger-ui
* [ローカルUI](http://localhost:8080/swagger-ui/index.html)
* [yamlファイル](http://localhost:8080/v3/api-docs.yaml)


### 参考
### DOMA
* [DOMA](http://doma.seasar.org/index.html)
* [DOMA2](https://doma.readthedocs.io/en/latest/)
* [HikariCPによるMySQLのJDBCの推奨設定](https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration)

### OpenAPI
* [springdoc-openapi：Getting Started](https://springdoc.org/#getting-started)
