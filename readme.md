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


## flyway

* 依存の追加
  * build.gradle参照
* application.yamlの修正
* resources/db/migration
  * V__1_hoge_fuga.sql
  * R__1_fuga_hoge.sql
