package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Statistics {

    String name;
    long total;
    short lowPrioritiesPercentage;
    short mediumPrioritiesPercentage;
    short highPrioritiesPercentage;
    long justAdded;
    long inProgress;
    long waitingForParts;
    long toInvoice;
}
