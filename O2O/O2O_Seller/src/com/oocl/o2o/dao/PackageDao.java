package com.oocl.o2o.dao;

import java.util.List;
import com.oocl.o2o.pojo.Package;
import com.oocl.o2o.util.SearchCriteria;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public interface PackageDao {
    int addPackage(Package pkg);

    int deletePackage(Package pkg);
    int deletePackageList(List<Package> packageList);

    int updatePackage(Package pkg);
    int updatePackageList(List<Package> packageList);

    Package getById(Integer packageId);
    List<Package> findAllByUserId(Integer userId);
    List<Package> findAllBySearchCriteria(SearchCriteria searchCriteria);

}
