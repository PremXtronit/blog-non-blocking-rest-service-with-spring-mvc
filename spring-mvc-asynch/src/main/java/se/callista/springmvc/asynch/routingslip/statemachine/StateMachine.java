package se.callista.springmvc.asynch.routingslip.statemachine;

import org.springframework.stereotype.Component;
import se.callista.springmvc.asynch.util.LogHelper;

import java.util.Iterator;

/**
 * A stateless minimal state machine
 */
@Component
public class StateMachine {

    public void initProcessing(Iterator<Processor> processingSteps, LogHelper log, StateMachineCallback completionCallback) {
        executeNextStep(new State(processingSteps, log, completionCallback));
    }

    public void executeNextStep(State state) {

        if (state.getProcessingSteps().hasNext()) {
            // Initiate next processing step...
            state.incrementProcessingStepNo();
            state.getProcessingSteps().next().process(state);

        } else {
            // We are done...
            state.getCompletionCallback().onCompleted(state);
        }
    }

}
