package net.mokus.azara.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.mokus.azara.Azara;

public class ModSounds {

    public static final SoundEvent BOING =registerSoundEvent("boing");



    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(Azara.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT, id ,SoundEvent.of(id));
    }

    public static void registerSounds(){

    }
}
