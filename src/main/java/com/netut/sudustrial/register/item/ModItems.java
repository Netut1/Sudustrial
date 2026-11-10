package com.netut.sudustrial.register.item;

import com.netut.sudustrial.register.item.core.RegisterCore;
import com.netut.sudustrial.register.item.nuclear_antidote.RegisterNuclearAntidote;

public class ModItems {
    public static void register() {
        RegisterCore.register();
        RegisterNuclearAntidote.register();
    }
}
