package com.volkruss.transactiontest.domain.model.item

import spock.lang.Specification

class ItemTest extends Specification {
    def test(){
        when:
            def hoge = "a"
        then:
            hoge == "a"
    }
}
