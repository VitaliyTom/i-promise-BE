package com.exadel.ipromise.extractors;

import com.exadel.ipromise.entity.Promise;
import com.exadel.ipromise.entity.Reason;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtractorPromise implements ResultSetExtractor<List<Promise>> {

    @Override
    public List<Promise> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Promise> promisesDetailList = new ArrayList<>();

        while (rs.next()) {
            Promise promise = new Promise();
            Reason reason = new Reason();

            promise.setPromiseId(rs.getLong(1));
            promise.setUserId(rs.getLong(2));
            promise.setAddictionId(rs.getLong(3));
            promise.setStartDateStamp(rs.getLong(4));
            promise.setAmountDays(rs.getLong(5));
            promise.setNameAddiction(rs.getString(7));

            if (rs.getLong(1) == rs.getLong(9)) {
                reason.setReasonId(rs.getLong(8));
                reason.setPromiseId(rs.getLong(9));
                reason.setReason(rs.getString(10));
            }

            promise.setReason(reason);
            promisesDetailList.add(promise);
        }
        return promisesDetailList;
    }
}
