package com.jkb.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.*;
import com.aliyuncs.goodstech.model.v20191230.ClassifyCommodityRequest;
import com.aliyuncs.goodstech.model.v20191230.ClassifyCommodityResponse;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest.Task;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageResponse;
import com.aliyuncs.imageenhan.model.v20190930.*;
import com.aliyuncs.imageenhan.model.v20190930.RecolorImageRequest.ColorTemplate;
import com.aliyuncs.imagerecog.model.v20190930.*;
import com.aliyuncs.imageseg.model.v20191230.*;
import com.aliyuncs.objectdet.model.v20191230.DetectMainBodyRequest;
import com.aliyuncs.objectdet.model.v20191230.DetectMainBodyResponse;
import com.aliyuncs.objectdet.model.v20191230.DetectVehicleRequest;
import com.aliyuncs.objectdet.model.v20191230.DetectVehicleResponse;
import com.aliyuncs.ocr.model.v20191230.*;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangliang
 * @date 2020/8/11
 */
@Component
@Slf4j
public class OcrUtil {


    @Value("${ali.ocr.regionId}")
    private String regionId;

    @Value("${ali.ocr.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.ocr.secret}")
    private String secret;

    @Value("${ali.ocr.host}")
    private String host;

    @Value("${ali.ocr.path}")
    private String path;

    @Value("${ali.ocr.appcode}")
    private String appcode;

    private static ThreadLocal<IAcsClient> threadLocal = new ThreadLocal<>();

    private void init(){
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,             //默认
                accessKeyId,         //您的AccessKeyID
                secret);    //您的AccessKeySecret
        IAcsClient  client = new DefaultAcsClient(profile);
        threadLocal.set(client);
    }


//    public OcrResponse getOcrAll(String faceUrl, String backUrl) {
//        OcrResponse ocrResponse = new OcrResponse();
//        Pair<Boolean, OcrResponse> face = getOcrInfo("face", faceUrl);
//        Pair<Boolean, OcrResponse> back = getOcrInfo("back", backUrl);
//
//        if (face.getKey()) {
//            BeanUtil.copyProperties(face.getValue(), ocrResponse, CopyOptions.create().setIgnoreNullValue(true));
//        }
//
//        if (back.getKey()) {
//            BeanUtil.copyProperties(back.getValue(), ocrResponse, CopyOptions.create().setIgnoreNullValue(true));
//        }
//
//
//        return ocrResponse;
//    }
//    /**
//     *
//     * @param side  身份证正面照 或 身份证反面照
//     * @param
//     * @return
//     */
//    public Pair<Boolean, OcrResponse> getOcrInfo(String side, String url) {
//        try {
//            init();
//
//            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//            map.add("Action", "RecognizeIdentityCard");
//            map.add("ImageURL", url);
//            map.add("side", side);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//            headers.add("Authorization", "APPCODE " + appcode);
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            HttpEntity<OcrResponse> response = restTemplate.postForEntity(host + path, request, OcrResponse.class);
//
//            return Pair.of(true,response.getBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }finally {
//            shutdown();
//        }
//
//        return Pair.of(false, null);
//    }

    private void shutdown(){
        IAcsClient iAcsClient = threadLocal.get();
        if (iAcsClient != null) {
            iAcsClient.shutdown();
        }
    }




    private  <R extends RpcAcsRequest<T>, T extends AcsResponse> T getAcsResponse(R req) throws Exception {
        try {
            init();
            return threadLocal.get().getAcsResponse(req);
        } catch (ServerException e) {
            // 服务端异常
            log.error(String.format("ServerException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (ClientException e) {
            // 客户端错误
            log.error(String.format("ClientException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (Exception e) {
            log.error("Exception:" + e.getMessage());
            throw e;
        }finally {
            shutdown();
        }
    }

    public  ChangeImageSizeResponse changeImageSize(String imageURL) throws Exception {
        log.info("--------  尺寸变换 --------------");
        ChangeImageSizeRequest req = new ChangeImageSizeRequest();
        req.setUrl(imageURL);
        req.setWidth(800);
        req.setHeight(600);
        ChangeImageSizeResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  ExtendImageStyleResponse extendImageStyle(String styleUrl,String majorUrl) throws Exception {
        ExtendImageStyleRequest req = new ExtendImageStyleRequest();
        log.info("--------  图像风格迁移 --------------");
        req.setStyleUrl(styleUrl);
        req.setMajorUrl(majorUrl);
        ExtendImageStyleResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  MakeSuperResolutionImageResponse makeSuperResolutionImage(String url) throws Exception {
        MakeSuperResolutionImageRequest req = new MakeSuperResolutionImageRequest();
        log.info("--------  清晰化/超分辨 ----");
        req.setUrl(url);
        MakeSuperResolutionImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  RecognizeImageColorResponse recognizeImageColor(String url) throws Exception {
        RecognizeImageColorRequest req = new RecognizeImageColorRequest();
        log.info("--------  色板识别 ----");
        req.setUrl(url);
        RecognizeImageColorResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  RecognizeImageStyleResponse recognizeImageStyle(String url) throws Exception {
        RecognizeImageStyleRequest req = new RecognizeImageStyleRequest();
        log.info("--------  风格识别 ----");
        req.setUrl(url);
        RecognizeImageStyleResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  RecolorImageResponse recolorImage() throws Exception {
        RecolorImageRequest req = new RecolorImageRequest();
        log.info("--------  拓色 ----");
        req.setUrl(
                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-1-src.png");
        req.setColorCount(3);
        //（选填）拓色模式，默认AUTO，取值范围[AUTO：自动拓色, TEMPLATE：色板拓色, REF_PIC：参考图拓色]
        req.setMode("AUTO");

        RecolorImageResponse resp = null;
        //AUTO
        log.info("自动拓色");
        resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        //RefUrl TODO
        log.info("参考图拓色");
        req.setMode("REF_PIC");
        req.setRefUrl(
                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-refurl-src.png");
        resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        log.info("色板拓色");
        req.setMode("TEMPLATE");
        req.setUrl(
                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-2-src.png");
        List<ColorTemplate> colorTemplateList = new ArrayList<>();
        colorTemplateList.add(buildColorTemplate("056A6B"));
        colorTemplateList.add(buildColorTemplate("FF0000"));
        colorTemplateList.add(buildColorTemplate("00FF00"));
        req.setColorTemplates(colorTemplateList);
        resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  ColorTemplate buildColorTemplate(String color) {
        ColorTemplate colorTemplate = new ColorTemplate();
        colorTemplate.setColor(color);
        return colorTemplate;
    }

    public  DetectImageElementsResponse detectImageElements(String url) throws Exception {
        log.info("--------  元素检测 -----");
        DetectImageElementsRequest req = new DetectImageElementsRequest();
        req.setUrl(url);
        DetectImageElementsResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  DetectFaceResponse detectFace(String imageURL) throws Exception {
        log.info("--------  人脸检测定位 --------------");
        DetectFaceRequest req = new DetectFaceRequest();
        req.setImageURL(imageURL);
        DetectFaceResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  DetectMainBodyResponse detectMainBody(String imageURL) throws Exception {
        log.info("--------  主体检测 --------------");
        DetectMainBodyRequest req = new DetectMainBodyRequest();
        req.setImageURL(imageURL);
        DetectMainBodyResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  ClassifyCommodityResponse classifyCommodity(String imageURL) throws Exception {
        log.info("--------  商品分类 --------------");
        ClassifyCommodityRequest req = new ClassifyCommodityRequest();
        req.setImageURL(imageURL);
        ClassifyCommodityResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  SegmentCommonImageResponse segmentCommonImage(String imageURL) throws Exception {
        log.info("--------  图像裁剪 --------------");
        SegmentCommonImageRequest req = new SegmentCommonImageRequest();
        req.setImageURL(imageURL);
        SegmentCommonImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  SegmentBodyResponse segmentBody(String imageURL) throws Exception {
        log.info("--------  人像分割 --------------");
        SegmentBodyRequest req = new SegmentBodyRequest();
        req.setImageURL(imageURL);
        SegmentBodyResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    //商品分割
    public  SegmentCommodityResponse segmentCommodity(String imageURL) throws Exception {
        log.info("--------  商品分割 --------------");
        SegmentCommodityRequest req = new SegmentCommodityRequest();
        req.setImageURL(imageURL);
        SegmentCommodityResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  RecognizeLicensePlateResponse recognizeLicensePlate(String imageURL) throws Exception {
        log.info("--------  车牌识别 --------------");
        RecognizeLicensePlateRequest req = new RecognizeLicensePlateRequest();
        req.setImageURL(imageURL);
        RecognizeLicensePlateResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  RecognizeBankCardResponse recognizeBankCard(String imageURL) throws Exception {
        log.info("--------  银行卡识别 --------------");
        RecognizeBankCardRequest req = new RecognizeBankCardRequest();
        req.setImageURL(imageURL);
        RecognizeBankCardResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    /**
     * @param side 可选值 face(正面) back（反面）
     * @param imageURL
     * @throws Exception
     */
    public  RecognizeIdentityCardResponse recognizeIdentityCard(String side,String imageURL) throws Exception {
        log.info("--------  身份证识别 --------------");
        RecognizeIdentityCardRequest req = new RecognizeIdentityCardRequest();
        req.setSide(side);
        req.setImageURL(imageURL);
        RecognizeIdentityCardResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public  ScanImageResponse scanImage(String imageURL) throws Exception {
        log.info("--------  内容审核 --------------");
        ScanImageRequest req = new ScanImageRequest();
        List<String> scenes = new ArrayList<String>();
        scenes.add("porn");
        req.setScenes(scenes);
        List<Task> tasks = new ArrayList<Task>();
        com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest.Task task = new Task();
        task.setDataId(UUID.randomUUID().toString());
        task.setImageURL(imageURL);
        tasks.add(task);
        req.setTasks(tasks);

        ScanImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    //场景识别
    public  RecognizeSceneResponse recognizeScene(String imageURL) throws Exception {
        log.info("--------  场景识别 --------------");
        RecognizeSceneRequest req = new RecognizeSceneRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeSceneResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    //表格识别
    public  RecognizeTableResponse recognizeTable(String imageURL) throws Exception {
        log.info("--------  表格识别 --------------");
        RecognizeTableRequest req = new RecognizeTableRequest();
        req.setUseFinanceModel(false);
        req.setAssureDirection(false);
        req.setHasLine(false);
        req.setSkipDetection(false);
        req.setOutputFormat("json");
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeTableResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //通用图像打标
    public  TaggingImageResponse testTaggingImage(String imageURL) throws Exception {
        log.info("--------  通用图像打标 --------------");
        TaggingImageRequest req = new TaggingImageRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        TaggingImageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //机动车检测
    public  DetectVehicleResponse testDetectVehicle(String imageURL) throws Exception {
        log.info("--------  机动车检测 --------------");
        DetectVehicleRequest req = new DetectVehicleRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        DetectVehicleResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //人脸属性识别
    public  RecognizeFaceResponse recognizeFace(String imageURL) throws Exception {
        log.info("--------  人脸属性识别 --------------");
        RecognizeFaceRequest req = new RecognizeFaceRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeFaceResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //火车票识别
    public  RecognizeTrainTicketResponse recognizeTrainTicket(String imageURL) throws Exception {
        log.info("--------  火车票识别 --------------");
        RecognizeTrainTicketRequest req = new RecognizeTrainTicketRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeTrainTicketResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    /**
     * 
     * @param side 可选值 face(正面) back（反面）
     * @param imageURL
     * @throws Exception
     */
    //驾驶证识别
    public RecognizeDriverLicenseResponse recognizeDriverLicense(String side,String imageURL) throws Exception {
        log.info("--------  驾驶证识别 --------------");
        RecognizeDriverLicenseRequest req = new RecognizeDriverLicenseRequest();
        req.setSide(side);
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeDriverLicenseResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //名片识别
    public RecognizeBusinessCardResponse recognizeBusinessCard(String imageURL) throws Exception {
        log.info("--------  名片识别 --------------");
        RecognizeBusinessCardRequest req = new RecognizeBusinessCardRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeBusinessCardResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //出租车发票识别
    public RecognizeTaxiInvoiceResponse recognizeTaxiInvoice(String imageURL) throws Exception {
        log.info("--------  出租车发票识别 --------------");
        RecognizeTaxiInvoiceRequest req = new RecognizeTaxiInvoiceRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeTaxiInvoiceResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //VIN码识别
    public RecognizeVINCodeResponse recognizeVINCode(String imageURL) throws Exception {
        log.info("--------  VIN码识别 --------------");
        RecognizeVINCodeRequest req = new RecognizeVINCodeRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeVINCodeResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //人脸比对
    public CompareFaceResponse testCompareFace(String imageURLA,String imageURLB) throws Exception {
        log.info("--------  人脸比对 --------------");
        CompareFaceRequest req = new CompareFaceRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURLA(imageURLA);
        req.setImageURLB(imageURLB);

        CompareFaceResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //公章识别
    public RecognizeStampResponse recognizeStamp(String imageURL) throws Exception {
        log.info("--------  公章识别 --------------");
        RecognizeStampRequest req = new RecognizeStampRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeStampResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //通用识别
    public RecognizeCharacterResponse recognizeCharacter(String imageURL) throws Exception {
        log.info("--------  通用识别 --------------");
        RecognizeCharacterRequest req = new RecognizeCharacterRequest();
        req.setMinHeight(10);
        req.setOutputProbability(true);
        req.setImageURL(imageURL);
        RecognizeCharacterResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //营业执照识别
    public RecognizeBusinessLicenseResponse recognizeBusinessLicense(String imageURL) throws Exception {
        log.info("--------  营业执照识别 --------------");
        RecognizeBusinessLicenseRequest req = new RecognizeBusinessLicenseRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeBusinessLicenseResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //户口页识别
    public RecognizeAccountPageResponse recognizeAccountPage(String imageURL) throws Exception {
        log.info("--------  户口页识别 --------------");
        RecognizeAccountPageRequest req = new RecognizeAccountPageRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeAccountPageResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }
    //行驶证识别
    public RecognizeDrivingLicenseResponse recognizeDrivingLicense(String side,String imageURL) throws Exception {
        log.info("--------  行驶证识别 --------------");
        RecognizeDrivingLicenseRequest req = new RecognizeDrivingLicenseRequest();
        req.setSide(side);
        // 注意：下面的链接换成自有的oss链接
        req.setImageURL(imageURL);
        RecognizeDrivingLicenseResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);

        return resp;
    }

    public static void printResponse(String actionName, String requestId, AcsResponse data) {
        log.info(String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data) ));
    }
}
