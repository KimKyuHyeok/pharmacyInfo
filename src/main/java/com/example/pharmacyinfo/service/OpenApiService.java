package com.example.pharmacyinfo.service;

import com.example.pharmacyinfo.entity.Pharmacy;
import com.example.pharmacyinfo.entity.PharmacyResponse;
import com.example.pharmacyinfo.repository.OpenApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OpenApiService {

    @Value("${api.url}")
    private String apiURL;

    private final OpenApiRepository openApiRepository;

    public List<PharmacyResponse> responsesDataList() {

        List<PharmacyResponse> list = entityToResponse(openApiRepository.findAll());

        return list;
    }

    public void getOpenApiDataList() {

        try {
            int min = 1;
            int max = 1000;
            int total = getTotal();

            List<Pharmacy> pharmacyList = new ArrayList<>();


            while (min < total) {
                String url = apiURL + min + "/" + max;

                Document document = DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(url);

                document.getDocumentElement().normalize();
                NodeList list = document.getElementsByTagName("row");
                NodeList tntList = document.getElementsByTagName("list_total_count");
                Element tntEle = (Element) tntList.item(0);
                total = Integer.parseInt(tntEle.getTextContent());

                for (int i = 0; i < list.getLength(); i++) {
                    Node node = list.item(i);

                    Element element = (Element) node;

                    pharmacyList.add(pharmacyBuild(element));
                }

                min += 1000;
                max += 1000;
            }

            System.out.println("RESULT " + pharmacyList.size());

            savePharmacyList(pharmacyList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // tag 값 가져오기
    private static String getTagValue(String tag, Element element) {
        NodeList list = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node value = (Node) list.item(0);

        if (value == null) {
            return null;
        }
        return value.getNodeValue();
    }

    // tag 값 entity 변환
    private static Pharmacy pharmacyBuild (Element element) {
        Pharmacy pharmacy = Pharmacy.builder()
                .address(getTagValue("DUTYADDR", element))
                .name(getTagValue("DUTYNAME", element))
                .telNumber(getTagValue("DUTYTEL1", element))
                .monEnd(getTagValue("DUTYTIME1C", element))
                .tueEnd(getTagValue("DUTYTIME2C", element))
                .wedEnd(getTagValue("DUTYTIME3C", element))
                .thuEnd(getTagValue("DUTYTIME4C", element))
                .friEnd(getTagValue("DUTYTIME5C", element))
                .satEnd(getTagValue("DUTYTIME6C", element))
                .sunEnd(getTagValue("DUTYTIME7C", element))
                .holEnd(getTagValue("DUTYTIME8C", element))
                .monStart(getTagValue("DUTYTIME1S", element))
                .tueStart(getTagValue("DUTYTIME2S", element))
                .wedStart(getTagValue("DUTYTIME3S", element))
                .thuStart(getTagValue("DUTYTIME4S", element))
                .friStart(getTagValue("DUTYTIME5S", element))
                .satStart(getTagValue("DUTYTIME6S", element))
                .sunStart(getTagValue("DUTYTIME7S", element))
                .holStart(getTagValue("DUTYTIME8S", element))
                .lon(getTagValue("WGS84LON", element))
                .lat(getTagValue("WGS84LAT", element))
                .build();

        return pharmacy;
    }

    // 데이터 최신화
    @Transactional
    public void savePharmacyList(List<Pharmacy> saveList) {
        openApiRepository.deleteAll();
        openApiRepository.saveAll(saveList);
    }


    // API 총 데이터 개수 가져오기
    public int getTotal() throws Exception {

        String url = apiURL + "1/2";

        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(url);

        document.getDocumentElement().normalize();
        NodeList list = document.getElementsByTagName("row");
        NodeList tntList = document.getElementsByTagName("list_total_count");
        Element tntEle = (Element) tntList.item(0);
        int total = Integer.parseInt(tntEle.getTextContent());

        return total;
    }

    // entity 데이터 response 데이터로 변환
    public List<PharmacyResponse> entityToResponse(List<Pharmacy> pharmacies) {

        List<PharmacyResponse> responses = new ArrayList<>();

        for (Pharmacy pharmacy : pharmacies) {
            PharmacyResponse response = PharmacyResponse.builder()
                    .name(pharmacy.getName())
                    .address(pharmacy.getAddress())
                    .telNumber(pharmacy.getTelNumber())
                    .mon(pharmacy.getMonStart() + "-" + pharmacy.getMonEnd())
                    .tue(pharmacy.getTueStart() + "-" + pharmacy.getTueEnd())
                    .wed(pharmacy.getWedStart() + "-" + pharmacy.getWedEnd())
                    .thu(pharmacy.getThuStart() + "-" + pharmacy.getThuEnd())
                    .fri(pharmacy.getFriStart() + "-" + pharmacy.getFriEnd())
                    .sat(pharmacy.getSatStart() + "-" + pharmacy.getSatEnd())
                    .sun(pharmacy.getSunStart() + "-" + pharmacy.getSunEnd())
                    .hol(pharmacy.getHolStart() + "-" + pharmacy.getHolEnd())
                    .lon(pharmacy.getLon())
                    .lat(pharmacy.getLat())
                    .build();

            responses.add(response);
        }

        return responses;
    }



}
