package org.xujin.springboot.service;

import org.springframework.stereotype.Service;
import org.xujin.springboot.Personal;
import org.xujin.springboot.ScoreRanking;

/**
 * @author xujin
 * @createtime 2020-08-22 19:56
 * @description
 */
@Service
public class Hello1Service {
    @ScoreRanking
    public boolean say(Personal personal) {
        System.out.println(personal);
        //int i = 1/0;
        return true;
    }
}
