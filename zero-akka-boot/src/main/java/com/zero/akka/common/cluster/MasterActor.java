package com.zero.akka.common.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MasterActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef workerRouter;

    // Constructor to initialize the worker router
    public MasterActor(ActorRef workerRouter) {
        this.workerRouter = workerRouter;
    }

    public static Props props(ActorRef workerRouter) {
        return Props.create(MasterActor.class, workerRouter);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TaskMessage.class, msg -> {
                    log.info("Received task message: {}", msg.task);
                    workerRouter.tell(msg, getSelf()); // Forward task to worker router
                })
                .build();
    }
}
