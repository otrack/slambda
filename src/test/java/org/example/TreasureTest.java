package org.example;

import org.infinispan.creson.test.Emulation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class TreasureTest extends Emulation {
    Graph gr;
    Hero superMan;

    @BeforeMethod
    public void setUp() throws Exception {

        gr = new Graph(10);
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0 ; i < gr.getGraphSize() ; i++) {
            if (i>0) gr.addUndirectedPath(i,random.nextInt(i));
            System.out.println(gr.toString());
        }

        superMan = new Hero("SuperMan", gr);
    }

    @Test
    public void should_rank_heros() {
        superMan.play();
        System.out.println(superMan.getScore());
        assert superMan.getScore() == gr.nodes.size();
    }

    @Override
    protected int numberOfCaches(){
        return 3;
    }
}
