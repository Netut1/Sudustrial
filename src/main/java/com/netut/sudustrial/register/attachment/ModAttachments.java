package com.netut.sudustrial.register.attachment;

import com.netut.sudustrial.register.attachment.antidote.AntidoteCycle;
import com.netut.sudustrial.register.attachment.antidote.AntidoteUsesInCycle;

public class ModAttachments {
    public static void register() {
        AntidoteCycle.register();
        AntidoteUsesInCycle.register();
    }
}
