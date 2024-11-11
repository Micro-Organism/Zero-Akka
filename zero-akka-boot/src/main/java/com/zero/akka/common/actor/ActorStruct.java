package com.zero.akka.common.actor;

import akka.actor.AbstractActor;
import com.zero.akka.domain.entity.SystemUserEntity;


public class ActorStruct extends AbstractActor {
    private final SystemUserEntity user;

    public ActorStruct(SystemUserEntity userModel){
        this.user = userModel;
    }

    //process msg
    @Override
    public Receive createReceive() {

        Receive build = receiveBuilder().match(String.class,(msg)-> {
            System.out.println(msg);
            sender().tell(" I am  a result of ActorStruct:"+user.getName(), self());
        }).match(Integer.class,(msg)-> {
            System.out.println(msg+"1");
        }).build();
        return build;
    }
}