package org.nclc.service;

import java.sql.SQLException;
import java.util.List;
import org.nclc.content.Sample;

public interface SampleService {

    public Sample createSample(Sample sample) throws SQLException;

    public List<Sample> findAllSamples() throws SQLException ;

    public Sample findSampleById(Integer id) throws SQLException;

    public void saveSample(Sample sample) throws SQLException;

    public void updateSample(Sample sample) throws SQLException;

    void deleteSample(Sample sample) throws SQLException;
    
    int countTotal() throws SQLException;

    /**
     * 
     * @param sample
     * @return Sample instance if not exists, otherwise existing instance
     * @throws SQLException 
     */
    public Sample isIn(Sample sample) throws SQLException;

}
