package com.netut.sudustrial.register.sound.party_explode;

import com.netut.sudustrial.Sudustrial;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;

public class RegisterPartyExplodeSound {
    public static SoundEvent PARTY_EXPLODE;

    public static void register() {
        Identifier partyExplodeLoc = Identifier.fromNamespaceAndPath(Sudustrial.MOD_ID, "party_explode");
        ResourceKey<SoundEvent> soundKey = ResourceKey.create(Registries.SOUND_EVENT, partyExplodeLoc);

        // Создаём SoundEvent с указанным идентификатором
        PARTY_EXPLODE = SoundEvent.createVariableRangeEvent(partyExplodeLoc);

        // Регистрируем в BuiltInRegistries.SOUND_EVENT
        Registry.register(BuiltInRegistries.SOUND_EVENT, soundKey, PARTY_EXPLODE);
    }
}
