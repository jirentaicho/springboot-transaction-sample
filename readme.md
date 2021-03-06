# トランザクションマネージャを使う

[トランザクションマネージャに関してのまとめ](https://volkruss.com/?p=2420)

* 各ブランチについて、トランザクションマネージャについて説明しています。

## 機能概要

受注時に在庫の整合性を確認して受注と在庫の更新を行います。
* OKパターン
  * 受注時に在庫数-受注件数がマイナス値にならない
* NGパターン
  * 受注時に在庫数-受注件数がマイナス値になる
  

以下の**伝搬属性**について確認します

* PROPAGATION_REQUIRES_NEW
  * 新しいトランザクションを開始
* PROPAGATION_REQUIRED
  * 既存のトランザクションに参加

## パターン

* 受注の登録時に在庫の更新を行わ

## JPA

直接JPAを利用します。  
※SpringDataJPAはDAOの自動生成だが柔軟性が落ちる。

* EntityManager
  * SQLの発行、ドメインとレコードの変換を行う
  * EntityManagerFactoryから取得できる
  * DAOクラスなどでインジェクションして利用する
  
## 更新時

更新時のモデル変換の流れ  
EntityModel(取得) → ドメインオブジェクト → EntityModel(更新)

* newしたEntityModelを更新する際にはmergeを行ってます
  * BaseDao#update参照

## バリデーション

依存
implementation 'org.springframework.boot:spring-boot-starter-validation'


## リクエスト処理

* StockQuery
  * アクセサのみ
  * データアクセス処理に共通しているクエリです
    * findById
    * findAll
  * Query → Criteriaにマッピングする
* @RestControllerを作成する
  * postやputのバリデーションは(途中)
  * ....................................工事中....

# Stock検索機能を追加する

今回のStockはマイナス在庫を許さないことにしています

* StockQueryクラスの作成
  * 検索に必要な条件を持っています
  * 画面側が提供するformと同義です
* 検索モデル(Criteria)の作成
  * StockCriteria
  * 基本的に検索モデルなのでDBカラムに紐づいてる
    * 厳密にはテーブルカラムに紐づくモデルは境界を超えてはいけない
    * →　テーブルカラムはインフラストラクチャ層にあり、それを継承したCriteriaをドメイン層に作成する
  * Queryを検索モデルに変換してサービス層に渡します
* 返却用モデルの作製を行います
  * StockOut
  * 返却用基底モデルにList<T extends DTO>のようなものを用意
  * コントローラーが返すResourceモデルの変数にマッチします
* コントローラーを作成します
  * findメソッドの引数にStockQueryを設定します
  * Queryを検索モデルに変換します
  * サービスに検索モデルを渡します
  * サービスから返却用モデルを受け取ります
  * 返却用モデルを 返却オブジェクトに変換して返す

## 返却オブジェクト

* GetでもPostでも基本はResourceインターフェースの実装を返却する
  * 返却モデル(OutDto)とMessageを格納したオブジェクトです

## Stock追加機能追加

* Requestクラスの作成
  * StockRequest
  * 単項目チェックをするためにアノテーションを付与しておく
* サービスクラスにrequestを渡してStockOutを得る
* GETと同様にResourceを作製して返却する

## 相関チェック

順序

1. バリデータークラス(Validatorの実装クラス)の作成
2. エラーメッセージはメッセージファイルなどに定義する
3. InitBinderにてバリデータークラスをWebDataBinderに登録する
4. 引数はErrorsが入ったBindingResultを利用する

## エラー処理

以下の場合にエラー処理を発生させる(例外)

* バリデーション
* ドメインルールのチェック

エラーを横断的関事としてAOPにて捌きます

順序

1. RuntimeExceptionを継承したエラーを作成する(トランザクションでロールバックさせるため)
2. RestControllerAdviceアノテーションを付ける。ResponseEntityExceptionHandlerを継承する。
3. @ExceptionHandlerに1.で作成したクラスを指定する
4. そこで例外クラスを処理できる
5. Resourceを返却すればJSONとして取得できる

その他

* 例外クラス自体にErrorsを持たせることでAdviceから取得できるようにします
* あとはRestControllerAdvice#@ExceptionHandlerのメソッドで例外クラスからErrorsを取得する


[参考](https://volkruss.com/?p=1557)

## flyway

* 依存の追加
  * build.gradle参照
* application.yamlの修正
* resources/db/migration
  * V1__hoge_fuga.sql
  * R__1_fuga_hoge.sql


