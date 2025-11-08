# Kinetic Minecart
This mod increases damage to moving minecarts; the faster they move, the higher the damage, with virtually no damage limit!

## Loader
[![Available for Fabric](https://img.shields.io/badge/Available%20for-Fabric-b2a995)](https://fabricmc.net)

## Releases
[![Published in Github](https://img.shields.io/badge/Published%20in-GitHub-333333)](https://github.com/KSSJW/kinetic-minecart/releases)

[![Published in Modrith](https://img.shields.io/badge/Published%20in-Modrith-16ae55)](https://modrinth.com/mod/kinetic-minecart)

[![Published in CurseForge](https://img.shields.io/badge/Published%20in-CurseForge-da5b32)](https://www.curseforge.com/minecraft/mc-mods/kinetic-minecart)

## Gallery
![A Creeper that was hit](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/Big.png)

## Features
- The damage dealt by minecarts is related to their speed:
```
	Speed >= 6			-> Damage = Speed cubed;

	Speed > 2 and < 6 	-> Damage = Speed squared;

	Speed <= 2			-> Damage = 0;
```
> [!NOTE]
> Theoretically, there is **No Damage Limit**.

- After being hit by a minecart, the entity is propelled approximately the same distance as its speed.

- Minecarts do not stop after hitting an entity.

- The impact of a minecart has no effect on item entities.

## Compatibility
- It is recommended that you install the **Fabric API**.
