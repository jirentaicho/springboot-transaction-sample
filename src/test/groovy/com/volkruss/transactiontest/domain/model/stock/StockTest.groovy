package com.volkruss.transactiontest.domain.model.stock

import spock.lang.Unroll

class StockTest extends spock.lang.Specification{
    // テストケースはコメントで記載できる
    def "在庫カウント100からsubCountで30減らすと70になる"(){
        // givenブロックにはオブジェクトの準備など
        given: "Stockをcount100で生成する"
        def stock = new Stock(1,1,100)

        // whenブロックには動作の実行などします
        when: "countをsubCountで30減らす"
        stock.subCount(30)

        // thenブロックにはアサーションを記載
        then: "countが70になる"
        stock.getCount() == 70
    }

    def "在庫カウント100の時addCount10した後にsubCount10すると100になる"(){
        given: "Stockをcount100で生成する"
        def stock = new Stock(1,1,100)

        when: "countをaddCountで10増やす"
        stock.addCount(10)
        and: "countをsubCountで10減らす"
        stock.subCount(10)

        then: "countが100になる"
        stock.getCount() == 100
    }

    def "マイナス状態チェック"(){
        expect: ""
        // whereで定義している変数が利用可能
        stock.isMinus() == isMinus

        // 入力データと期待値を定義する
        where: "データと期待値を定義する"
        stock               || isMinus
        new Stock(1,1,100)  || false
        new Stock(1,1,-1)   || true
        new Stock(1,1,0)    || true //ここでエラー
        new Stock(1,1,1)    || false
        new Stock(1,1,-100) || true
    }

    def "マイナス状態チェック(パイプ記法)"(){
        expect: "isMinusメソッドがcountがマイナス値の場合はtrueを返す"
        // whereで定義している変数が利用可能
        stock.isMinus() == isMinus

        // 入力データと期待値を定義する
        where: "データと期待値を定義する"
        stock << [new Stock(1,1,100),new Stock(1,1,-100),new Stock(1,1,0)]
        isMinus << [false,true,false]
    }

}