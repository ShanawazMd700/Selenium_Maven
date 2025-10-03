package listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import utils.ExtentTestManager;

public class ExtentCucumberAdapter implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        // Register handlers for step start and step finish
        publisher.registerHandlerFor(TestStepStarted.class, this::handleStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
    }

    private void handleStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();

            String keyword = step.getStep().getKeyword(); // Given / When / Then
            String text = step.getStep().getText();       // Step text

            // Log step start
            ExtentTestManager.getTest().info(keyword + text);
        }
    }

    private void handleStepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();

            String keyword = step.getStep().getKeyword();
            String text = step.getStep().getText();

            io.cucumber.plugin.event.Status stepStatus = event.getResult().getStatus();

            switch (stepStatus) {
                case PASSED:
                    ExtentTestManager.getTest().pass(keyword + text + " ✅");
                    break;
                case FAILED:
                    ExtentTestManager.getTest().fail(keyword + text + " ❌ " + event.getResult().getError());
                    break;
                case SKIPPED:
                    ExtentTestManager.getTest().skip(keyword + text + " ⏭");
                    break;
                default:
                    ExtentTestManager.getTest().info(keyword + text + " ℹ️ (" + stepStatus + ")");
                    break;
            }
        }
    }
}
