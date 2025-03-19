package org.example;


import org.example.aplication.hapier.Hapier;
import org.example.domain.hl7.LIS.LISToNPLH.OML21.dto.OML21;
import org.example.domain.hl7.VTG.VTGToNPLH.BLOCKUPDATE.BlockUpdate;
import org.example.domain.mapper.host.ELIS;
import org.example.domain.message.Message;
import org.example.domain.message.conditions.EStatus;
import org.example.domain.message.entity.supplementalInfo.SpecialInstruction;

public class Main {
    public static void main(String[] args) {

        Message message = Message.Default("CASE-eb4089cd1a7d45");
        message.getSpecimen("CASE-eb4089cd1a7d45;A")
                .addSupplementalInfo(
                        new SpecialInstruction("SpecialInstructionValue"));


        OML21 oml21 = OML21.FromMessage(message);
        BlockUpdate blockUpdate = BlockUpdate.FromMessage(message, "STAINING");


        String oml21String = oml21.toString();
        String blockUpdateString = blockUpdate.toString();




        //OML21 oml21 = OML21.OneSlide("CASE-eb4089cd1a7d45");
        String msg = "MSH|^~\\&|LIS|XYZ Laboratory|Ventana|ABC Laboratory|20240518170503||OML^O21|MCI-dfd8a9885db74b68ab6089d654d86af1|P|2.4|\n" +
                "PID|||MN-10000000||LastName^FirstName^MiddleInitial^Sr.^Mr.||19800101|M|\n" +
                "PV1|||||||IndiID^ILastName^IFirstName^ImiddleName^Isufix^Iprefix^Iaddress^^city^Icountry^state^hometel^mobiletel^worktel^zipcode|\n" +
                "SAC|||||||20240518170503|\n" +
                "ORC|NW|CASE-eb4089cd1a7d45|||||||||||||||||||VMSI^Ventana Medical Systems|\n" +
                "OBR|1|CASE-eb4089cd1a7d45 A1-1||123^H. Advance1^STAIN|||20141014||||||||Breast^Left Breast Upper Node^Breast Biopsy|IndiID^ILastName^IFirstName^ImiddleName^Isufix^Iprefix^Iaddress^^city^Icountry^state^hometel^mobiletel^^zipcode|worktel^^^Imail@e.com||CASE-eb4089cd1a7d45;A;1;1^1|CASE-eb4089cd1a7d45;A;1^1|CASE-eb4089cd1a7d45;A^A|||||||||||||IndiID^ILastName^IFirstName^ImiddleName|||||||||||||SPECIALINSTRUCTION^SPECIALINSTRUCTIONVALUE^PART^^~QUALITYISSUE^Tissue found^SLIDE^RESOLUTION^Continue processing~TISSUEPIECES^6^BLOCK^^~RECUT^RECUTVALUE^SLIDE^^~GROSSDESCRIPTION^GROSSDESCRIPTIONVALUE^PART^\n" +
                "ORC|NW|CASE-eb4089cd1a7d45|||||||||||||||||||VMSI^Ventana Medical Systems|\n" +
                "OBR|1|CASE-eb4089cd1a7d45 A1-1||123^H. Advance1^STAIN|||20141014||||||||Breast^Left Breast Upper Node^Breast Biopsy|IndiID^ILastName^IFirstName^ImiddleName^Isufix^Iprefix^Iaddress^^city^Icountry^state^hometel^mobiletel^^zipcode|worktel^^^Imail@e.com||CASE-eb4089cd1a7d45;A;1;2^2|CASE-eb4089cd1a7d45;A;1^1|CASE-eb4089cd1a7d45;A^A|||||||||||||IndiID^ILastName^IFirstName^ImiddleName";

        Hapier hapier = new Hapier();
        Message m = hapier.from(ELIS.OML21, msg);

        String statusUpdate = m.to(ELIS.STATUS_UPDATE, EStatus.IN_PROGRESS, m.getOrder("CASE-eb4089cd1a7d45"));
        String deleteSlide = m.to(ELIS.DELETE_SLIDE, m.getSlide("CASE-eb4089cd1a7d45;A;1;1"));
        String adtUpdate = m.to(ELIS.ADTA08, m.getOrder());
        String ack = m.to(ELIS.ACK);
        String deleteCase = m.to(ELIS.DELETE_CASE, m.getOrder());
        String deleteSpecimen = m.to(ELIS.DELETE_SPECIMEN, m.getSpecimen("CASE-eb4089cd1a7d45;A"));
        String scanSlide = m.to(ELIS.SCAN_SLIDE, m.getSlide("CASE-eb4089cd1a7d45;A;1;1"));
        String msgreturn = m.to(ELIS.OML21);

        System.out.println("ORIGINAL \n");
        System.out.println(msg + "\n");
        System.out.println("OML^O21 \n");
        System.out.println(msgreturn);
        System.out.println("STATUS UPDATE\n");
        System.out.println(statusUpdate);
        System.out.println("DELETE SLIDE\n");
        System.out.println(deleteSlide);
        System.out.println("ADT A08\n");
        System.out.println(adtUpdate);
        System.out.println("ACK\n");
        System.out.println(ack);
        System.out.println("DELETE CASE\n");
        System.out.println(deleteCase);
        System.out.println("DELETE SPECIMEN\n");
        System.out.println(deleteSpecimen);
        System.out.println("SCAN SLIDE\n");
        System.out.println(scanSlide);


//        String rescanOriginal = "MSH|^~\\&|Roche Diagnostics|VENTANA Connect|LIS|APLAB|20240606105333||OUL^R21|8DAF6D06-7F75-4A61-BC62-AEF63F8CE614|P|2.4\n" +
//                "ORC|RE||CASE-7789c0ea05be4d\n" +
//                "OBR|1||CASE-7789c0ea05be4d;A;1;1|SLIDE^sendRescanSlide\n" +
//                "OBX|1|ST|CASE-7789c0ea05be4d;A;1;1^SLIDE||isRescanned^TRUE||||||F\n" +
//                "OBX|2|ST|CASE-7789c0ea05be4d;A;1;1^SLIDE||rescanReason^Reason Test 1||||||F\n";
//        Message rescanLIS = hapier.from(ELIS.RESCAN_SLIDE, rescanOriginal);

        m.getAllSlides().get(0).setRescanReason("TEST");
        m.getAllSlides().get(0).setIsRescanned(true);

        String rescan = m.to(ELIS.RESCAN_SLIDE, m.getSlide("CASE-eb4089cd1a7d45;A;1;1"));
        System.out.println(rescan);
        String x = "x";

    }
}