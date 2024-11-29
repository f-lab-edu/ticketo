package org.flab.api.domain.event.api;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.Show;
import org.flab.api.domain.event.dto.show.ShowListResponse;
import org.flab.api.domain.event.dto.show.ShowSimpleResponse;
import org.flab.api.domain.event.service.ShowService;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ShowRequestResponseHelper {

    private final ShowService showService;

    public ShowListResponse getShowListResponse(long eventId) {
        List<Show> showList = showService.getShowListByEventId(eventId);
        List<ShowSimpleResponse> showSimpleResponseList = showList.stream().map(Show::toSimpleResponse).toList();
        return new ShowListResponse(showSimpleResponseList.size(), eventId, showSimpleResponseList);
    }
}
