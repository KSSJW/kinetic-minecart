# Kinetic Minecart
This mod increases damage to moving minecarts; the faster they move, the higher the damage, with virtually no damage limit!

## Loader
[![Fabric](https://img.shields.io/badge/Available%20for-Fabric-dbd0b4)](https://fabricmc.net) [![NeoForge](https://img.shields.io/badge/Available%20for-NeoForge-e68c37)](https://neoforged.net) [![Forge](https://img.shields.io/badge/Available%20for-Forge-2e435f)](https://files.minecraftforge.net/)

## Environment
![Client only](https://img.shields.io/badge/Side-Server%20and%20Client-3c8527)

## Releases
[![Github](https://img.shields.io/badge/Published%20in-GitHub-808284?logo=github&logoColor=white)](https://github.com/KSSJW/kinetic-minecart/releases) [![Modrinth](https://img.shields.io/badge/Published%20in-Modrinth-1bd96a?logo=modrinth&logoColor=white)](https://modrinth.com/mod/kinetic-minecart) [![CurseForge](https://img.shields.io/badge/Published%20in-CurseForge-f16436?logo=curseforge&logoColor=white)](https://www.curseforge.com/minecraft/mc-mods/kinetic-minecart)

## Compatibility
- ### Fabric
  - Fabric API (Required)
  - Cloth Config API (Recommended) (Custom configuration features)
  - Mod Menu (Recommended) (Mod configuration page)
- ### NeoForge
  - Cloth Config API (Recommended) (Custom configuration features)
- ### Forge
  - Cloth Config API (Recommended) (Custom configuration features)

## Progress
Check out the latest development progress here. [Development Progress](https://windysky.gitbook.io/main/minecraft/kinetic-minecart/version)

## Main Gallery
![A Creeper that was hit](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Big.png)

## Features
- The damage dealt by minecarts is related to their speed:
```
Speed >= 6			-> Damage = Speed cubed;

Speed > 2 and < 6 	-> Damage = Speed squared;

Target has Vehicle  -> Damage = Speed;

Speed <= 2			-> Damage = 0;
```
> [!NOTE]
> Theoretically, there is **No Damage Limit**.

- After being hit by a minecart, the entity is propelled approximately the same distance as its speed.

- Minecarts do not stop after hitting an entity.

- It offers custom configuration options, including customizing the collision effects of the minecart.

## Configuration
![Configuration1](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration1.png)
![Configuration2](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration2.png)
![Configuration3](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration3.png)

- You can edit the exclusion list, click on an item to name or delete it, and the inspection tool will attempt to proofread your input. (Sometimes, when you create a new item, the new text box may not appear; you may see an empty one, but this does not affect selection or input.)

![Exclusion List](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/ExclusionList.png)

- In multiplayer mode, you cannot change the configuration via the menu; instead, the server administrator needs to modify the 'kinetic-minecart.json' file. Below is an example:

![Config File](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/ConfigFile.png)

- selectedApplicaionMode -> Radius / Collide


- intRadius -> 3 ~ 10 (5 -> 0.5 block)

