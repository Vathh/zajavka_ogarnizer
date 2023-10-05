package pl.ogarnizer.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Statistics {

    long total;
    long lowPriorities;
    long mediumPriorities;
    long highPriorities;
    long justAdded;
    long inProgress;
    long waitingForParts;
    long toInvoice;
}
