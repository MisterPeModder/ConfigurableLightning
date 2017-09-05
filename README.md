Configurable Lightning
=========================

This mod adds some gamerules that allows lightning to be fully configurable.

#### Gamerules:

- lightningProbablity: <code>/gamerule lightningProbability &lt;integer&gt;</code>  
This number means there is 1 chance out of (number) that an EntityLightningBolt spawns when it's raining, setting it to 0 or lower will disable lightning bolts. (default: 100000)

- lightningFire: <code>/gamerule lightningFire &lt;boolean&gt;</code>  
Enables/disables fire created by ligthning. (default: true)

- lightningDamage: <code>/gamerule lightningDamage &lt;float&gt;</code>  
Damage dealt by ligthing (default: 5.0)

- lightningRange: <code>/gamerule lightningRange &lt;double&gt;</code>  
Entites inside this range will be damaged by ligthning (default: 3.0)

- lightningHorseSpawning:  <code>/gamerule lightningRange &lt;boolean&gt;</code>  
Should lightning spawn skeleton horses? (default: true)

Setting any of this values to an invalid string such as "covefe" will set it to its default vaulue.

As of version 1.1.0, HexianCore (https://minecraft.curseforge.com/projects/hexiancore) is needed.

#### Credits:
-CattyCat (http://www.minecraftforum.net/members/CattyCat), for suggesting the mod idea.

#### License:
All code (excluding the bundled APIs from other mods, which are covered by their respective licenses) are released under the CC BY-NC 3.0 license (https://creativecommons.org/licenses/by-nc/3.0/deed.en).
You are free to include this mod anywhere as long as you don't claim the mod as yours, You may not use the material for commercial purposes.
