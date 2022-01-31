package com.hardziyevich.groomer.servicetype;

import com.hardziyevich.groomer.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,Long> {

    @Query("SELECT DISTINCT st.type FROM ServiceType st JOIN st.groomerWorkTimes gwt WHERE gwt.day =?1")
    List<String> findServiceTypeByGroomerWorkTimesDay(LocalDate day);

    @Query("SELECT DISTINCT st.type FROM ServiceType st JOIN st.groomerWorkTimes gwt WHERE gwt.groomerId =?1")
    List<String> findServiceTypeByGroomerId(Long groomerId);

    @Query("SELECT DISTINCT st.type FROM ServiceType st JOIN st.groomerWorkTimes gwt WHERE gwt.groomerId=?1 AND gwt.day=?2")
    List<String> findServiceTypeByGroomerIdAndDay(Long groomerId, LocalDate day);

    @Query("SELECT gwt.id FROM ServiceType st JOIN st.groomerWorkTimes gwt WHERE st.type=?1")
    List<Long> findGroomerIdByService(String service);

}
