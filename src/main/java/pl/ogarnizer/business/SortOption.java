package pl.ogarnizer.business;

import lombok.Getter;
import lombok.Value;

//@Getter
@Value
public class SortOption {

    String keyword;
    String sortBy;
    String sortDir;
}
