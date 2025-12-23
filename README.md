# Kinetic Minecart
This mod increases damage to moving minecarts; the faster they move, the higher the damage, with virtually no damage limit!

## Loader
[![Fabric](https://img.shields.io/badge/Available%20for-Fabric-dbd0b4?logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAUCAYAAACAl21KAAAACXBIWXMAAA7EAAAOxAGVKw4bAAADpklEQVQ4jZXSW2hbdRwH8O//XHJO0+aetGmytLm0umbrvZ3OytauWBl0KA7RPcgeBB/0QScIQ/RJRGHCHuoFtQpT9EEQb4yN4fVlsC4ba7uMriatrWvanDT3y8nJOSd/X1S2dXXz+/zlw+/H7wfcQ4Z6w/0PDO+Mh8Oupu067N2QwcHeh9u8rlOPPzbesRTbCLY2u35fW5eSt/eYu0GE1CdbWuw7D+wbITt89qdFkTu+u8MX+t8QQzSHpirIZUp4/dVXMH7goSNGU+NZAJabe9uuNur3i94O34mnDk8e9niam06f+RH+dg/cLU4EQkFrIrE23upwn99IpVLbQn19fVbOzB+8vzPw1r6RAYvdbkE8vgwCwGa3wt3qIpmM5C0Ui4LTal1Zl9LJO0IBj/Wo0ch/cuzF53lXsw0tTgdGHhzEyamPwBADgu0+TEzsx/z81YHERqZL2sx8tgXaM7Tr7UcnRl949ugRm8NugqLIUGsqeIOAgeFBrK6u4peff0UwdB9mIpdRLJd9Po/7mX+hnp6exoDb8sTY2N7j3btC3s5gGwgLUFoHBaDrFAaBQ7lUglxRIUlZlMoyKMBUq7Kd+wfitVI/28B+fvCR/byn1QkAIABEUYSi1FAslEABBAN+NIgWvPf+NIaGh0FYnqYz+SwDAHsGwy9393d9/+n0FO9tcaJalpHezIJSAgIC0WCAy+lATdahahrA6KCU4kLkIubn5j6+EIk6uL1D3Se7wh1P9u4O2QjRAKqD4zk0MEaUShUQQsCzLATBAAC4vhBHdHF5U9PxGsNwcUZQFwCAo1R7aUerG6FgEIpSg8hz4HgWDMciV5Gh64DGs2BYDplsHouxP/OXr1x7d2Z24cObj8RRAr1UqTLJVIbUFAWdIT9EgQchddjtVkipNPKFMnKFMj6YPlWXsoUTc1fjb95+bdbjdf6myKo/n8sHml3N0OsUBECDUQBAIMtVLC2v4bvT53A9tvYG4RqnJEmqboESic0Vh9laKJTKo2azqUkQeHA8B47lUNcp4ksrmI3G6MVLc2dVmj8WjS4X7vTELADcWE8usKCFpLR+yNfmBSEEslyDqmr49oczmInM/hS5sjiZSlW2THILBAA2ho9JufxSJl8c1XRVEAURX3z5FTakLCj48zcSya+3Q26BstWqUpZr8xazyUZpvV2t1cxLy6soFKt/sODfSSSTi/cE/Z16Ukqfs5iExo2kNEbBgDDkucjstW/+CwGAvwAUxX1hlnD3nAAAAABJRU5ErkJggg==)](https://fabricmc.net)

## Environment
![Client only](https://img.shields.io/badge/Side-Server%20and%20Client-3c8527)

## Releases
[![Github](https://img.shields.io/badge/Published%20in-GitHub-808284?logo=github&logoColor=white)](https://github.com/KSSJW/kinetic-minecart/releases) [![Modrinth](https://img.shields.io/badge/Published%20in-Modrinth-1bd96a?logo=modrinth&logoColor=white)](https://modrinth.com/mod/kinetic-minecart) [![CurseForge](https://img.shields.io/badge/Published%20in-CurseForge-f16436?logo=curseforge&logoColor=white)](https://www.curseforge.com/minecraft/mc-mods/kinetic-minecart)

## Compatibility
- Fabric API (Required)
- Cloth Config API (Required)
- Mod Menu (Recommended)

## Progress
Check out the latest development progress here. [Development Progress](httpd://windysky.gitbook.io/main/minecraft/kinetic-minecart/version)

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

## Notes
- You can edit the exclusion list, click on an item to name or delete it, and the inspection tool will attempt to proofread your input. (Sometimes, when you create a new item, the new text box may not appear; you may see an empty one, but this does not affect selection or input.)

![Exclusion List](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/ExclusionList.png)

- In multiplayer mode, you cannot change the configuration via the menu; instead, the server administrator needs to modify the 'kinetic-minecart.json' file. Below is an example:

![Config File](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/ConfigFile.png)

## Configuration Gallery
![Configuration1](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration1.png)
![Configuration2](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration2.png)
![Configuration3](https://raw.githubusercontent.com/KSSJW/kinetic-minecart/refs/heads/dev/images/Configuration3.png)

