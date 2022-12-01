package com.kib.weblab3.beans;


import com.kib.weblab3.entity.TagEntity;
import com.kib.weblab3.math.HitResultCalculator;
import com.kib.weblab3.repository.HitCheckRepoImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class Table {

    private static final HitResultCalculator hitResultCalculator = new HitResultCalculator();

    @ManagedProperty(value = "#{repoBean}")
    private HitCheckRepoImpl repo;

    private Integer currentR = 1;

    private List<HitCheckResult> results = new ArrayList<>();

    private String appliedTags = "";

    public void clear() {
        results.clear();
        repo.deleteAllBySessionId(getSessionId());
    }

    public List<HitCheckResult> getAllResults() {
        results = repo.getAllResultsBySessionId(getSessionId());
        if (!appliedTags.isEmpty()) {
            List<TagEntity> tagEntities = processTagBean(appliedTags);
            List<HitCheckResult> resultsWithFilters = new ArrayList<>();
            for (HitCheckResult result : results) {
                for (TagEntity tag : result.getTagEntities()) {
                    if (tagEntities.contains(tag)) {
                        resultsWithFilters.add(result);
                        break;
                    }
                }
            }
            return resultsWithFilters;
        }
        return results;
    }


    public List<HitCheckResult> getAllResultsByR() {
        System.out.print("Results size is - ");
        System.out.println(results.size());
        if (!appliedTags.isEmpty()) {
            List<TagEntity> tagEntities = processTagBean(appliedTags);
            List<HitCheckResult> resultsWithFilters = new ArrayList<>();
            for (HitCheckResult result : results) {
                for (TagEntity tag : result.getTagEntities()) {
                    if (tagEntities.contains(tag)) {
                        resultsWithFilters.add(result);
                        break;
                    }
                }
            }
            System.out.print("Hello there");
            System.out.println(resultsWithFilters);
            return resultsWithFilters.stream().filter(p -> (int) p.getR() == currentR).collect(Collectors.toList());
        }
        return results.stream().filter(p -> (int) p.getR() == currentR).collect(Collectors.toList());
    }

    public void addToTable(HitCheckResult hitCheckResult, TagInputBean tagInputBean) {
        List<TagEntity> tagEntities = processTagBean(tagInputBean.getTags());
        hitCheckResult.setR(currentR);
        hitCheckResult.setHitResult(hitResultCalculator.calculateHitResult(hitCheckResult.getX(), hitCheckResult.getY(), hitCheckResult.getR())); //TODO сделать математику
        hitCheckResult.setSessionId(getSessionId());
        hitCheckResult.setDate(ZonedDateTime.now());
        if (!(tagEntities.size() == 1 && "".equals(tagEntities.get(0).getName())) && tagEntities.size() != 0) {
            hitCheckResult.setTagEntities(tagEntities);
        }
        results.add(hitCheckResult);
        repo.save(getSessionId(), hitCheckResult);
    }

    private String getSessionId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }

    private List<TagEntity> processTagBean(String tagInputBeanNames) {
        List<TagEntity> tags = new ArrayList<>();
        String[] splittedTags = tagInputBeanNames.split(",");
        TagEntity tagEntity;
        for (String elem : splittedTags) {
            tagEntity = new TagEntity();
            tagEntity.setName(elem.trim());
            tags.add(tagEntity);
        }
        return tags;
    }

//    public  getPointsByTags() {
//    }
}
