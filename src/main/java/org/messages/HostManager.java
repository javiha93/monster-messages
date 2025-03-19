package org.messages;

import org.messages.dto.Host;
import org.messages.dto.Message;

import java.util.ArrayList;
import java.util.List;

public class HostManager {

    private static Host LIS;
    private static Host VTG;
    private static Host UPATHCLOUD;

    private static Host DP600;

    static {

        List<Message> messagesLIS = new ArrayList<>();
        messagesLIS.add(new Message("OML^O21", "OML^O21"));
        messagesLIS.add(new Message("OUL^R21 UPDATE CASE", "OUL^R21UPDATECASE"));
        messagesLIS.add(new Message("OUL^R21 DELETE CASE", "OUL^R21DELETECASE"));
        messagesLIS.add(new Message("OUL^R21 DELETE SLIDE", "OUL^R21DELETESLIDE"));
        messagesLIS.add(new Message("OUL^R21 DELETE SPECIMEN", "OUL^R21DELETESPECIMEN"));
        messagesLIS.add(new Message("ADT", "ADT"));
        LIS = new Host("LIS", messagesLIS);

        List<Message> messagesVTG = new ArrayList<>();
        messagesVTG.add(new Message("SPECIMEN UPDATE", ""));
        messagesVTG.add(new Message("SLIDE UPDATE", ""));
        messagesVTG.add(new Message("ADDITION", ""));
        messagesVTG.add(new Message("BLOCK UPDATE", ""));
        messagesVTG.add(new Message("OE/WF", ""));
        messagesVTG.add(new Message("ADT^A08", ""));
        VTG = new Host("VTG", messagesVTG);

        List<Message> messagesUpathCloud = new ArrayList<>();
        messagesUpathCloud.add(new Message("SCAN SLIDE", "SCAN SLIDE"));
        messagesUpathCloud.add(new Message("RESCAN", "RESCAN SLIDE"));
        messagesUpathCloud.add(new Message("CASE REPORT", "CASEREPORTDATA"));
        messagesUpathCloud.add(new Message("FINAL CASE REPORT", "FINALCASEREPORT"));
        messagesUpathCloud.add(new Message("UPDATE CASE STATUS", ""));
        messagesUpathCloud.add(new Message("UPDATE PATHOLOGIST", "UPDATEPATHOLOGIST"));
        messagesUpathCloud.add(new Message("RELEASE SPECIMEN", "SEND RELEASED"));
        messagesUpathCloud.add(new Message("STAIN REQUEST", ""));
        messagesUpathCloud.add(new Message("SEND SLIDE WSADATA", ""));
        UPATHCLOUD = new Host("UPATH CLOUD", messagesUpathCloud);

        List<Message> messagesDP600 = new ArrayList<>();
        messagesDP600.add(new Message("SCAN SLIDE", "SCAN SLIDE"));
        messagesDP600.add(new Message("UPDATESLIDE", "UPDATESLIDE"));
        DP600 = new Host("DP 600", messagesDP600);
    }

    public static List<Host> getHosts() {
        List<Host> hosts = new ArrayList<>();
        hosts.add(LIS);
        hosts.add(VTG);
        hosts.add(UPATHCLOUD);
        hosts.add(DP600);

        return hosts;
    }
}
