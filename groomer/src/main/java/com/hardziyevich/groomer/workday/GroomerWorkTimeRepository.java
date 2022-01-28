package com.hardziyevich.groomer.workday;

import com.hardziyevich.groomer.entity.GroomerWorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GroomerWorkTimeRepository extends JpaRepository<GroomerWorkTime, Long> {

    @Query("SELECT DISTINCT gwt.day FROM GroomerWorkTime gwt ORDER BY gwt.day ASC")
    List<LocalDate> findDistinctDay();

    @Query("SELECT gwt.day FROM GroomerWorkTime gwt JOIN gwt.serviceTypes st WHERE st.type = ?1 ORDER BY gwt.day ASC")
    List<LocalDate> findGroomerWorkTimeByServiceType(String serviceType);

    @Query("SELECT DISTINCT gwt.day FROM GroomerWorkTime gwt WHERE gwt.groomerId = ?1 ORDER BY gwt.day ASC")
    List<LocalDate> findDistinctDayByGroomerId(Long groomerId);

    @Query("SELECT DISTINCT gwt.day FROM GroomerWorkTime gwt WHERE gwt.groomerId = ?1 AND gwt.serviceTypes.type=?2 ORDER BY gwt.day ASC")
    List<LocalDate> findDistinctDayByGroomerIdAndServiceType(Long groomerId, String serviceType);

    @Query("SELECT gwt.groomerId FROM GroomerWorkTime gwt WHERE gwt.day=?1")
    List<Long> findALlGroomerIdByWorkingDay(LocalDate day);

    @Query("SELECT gwt.groomerId FROM GroomerWorkTime gwt JOIN gwt.serviceTypes st WHERE gwt.day = ?1 AND st.type = ?2")
    List<Long> findAllGroomerIdByWorkingDayAndService(LocalDate localDate, String serviceType);

    @Query("SELECT gwt FROM GroomerWorkTime gwt JOIN gwt.serviceTypes st WHERE gwt.groomerId = ?1 AND gwt.day = ?2 AND st.type=?3")
    Optional<GroomerWorkTime> findGroomerWorkTimeByGroomerIdAndDayAndTypeService(Long groomerId, LocalDate day, String serviceType);

}
