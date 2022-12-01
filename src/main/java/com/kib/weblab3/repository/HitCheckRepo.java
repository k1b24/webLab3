package com.kib.weblab3.repository;

import com.kib.weblab3.beans.HitCheckResult;

import java.util.List;

public interface HitCheckRepo {

    void save(String sessionId, HitCheckResult hitCheckResult);

    void deleteAllBySessionId(String sessionId);

    List<HitCheckResult> getAllResultsBySessionId(String sessionId);

}
