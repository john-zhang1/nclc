package org.nclc.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.nclc.content.Sample;
import org.nclc.dao.SampleDao;
import org.nclc.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    /** log4j category */
    private static Logger log = Logger.getLogger(SampleServiceImpl.class);

    @Autowired
    private SampleDao sampleDao;


    @Override
    public Sample createSample(Sample sample) throws SQLException {
        if(sample!=null && sample.getId()==null){
            Sample s = isIn(sample);
            if(s == null){
                sampleDao.create(sample);
            } else {
                sample = s;
            }
        }

        return sample;
    }

    @Override
    public List<Sample> findAllSamples() throws SQLException {
         return sampleDao.findAll(Sample.class);
    }

    @Override
    public Sample findSampleById(Integer id) throws SQLException {
        return sampleDao.findByID(Sample.class, id);
    }

    @Override
    public void saveSample(Sample sample) throws SQLException {
        sampleDao.save(sample);
    }

    @Override
    public void updateSample(Sample sample) throws SQLException {
        sampleDao.update(sample);
    }

    @Override
    public void deleteSample(Sample sample) throws SQLException {
        sampleDao.delete(sample);
    }

    @Override
    public int countTotal() throws SQLException {
        return sampleDao.countRows();
    }

    @Override
    public Sample isIn(Sample sample) throws SQLException {
        List<Sample> samples = findAllSamples();
        Sample s = findSampleInstance(sample.getText(), sample.getErrorText(), samples);
        return s;
    }

    private Sample findSampleInstance(String text, String errorText, List<Sample> samples) {
        return samples.stream()
            .filter(sample -> sample.getText().toLowerCase().equals(text.toLowerCase()))
            .filter(sample -> sample.getErrorText().toLowerCase().equals(errorText.toLowerCase()))
            .findFirst()
            .orElse(null);
    }

}
