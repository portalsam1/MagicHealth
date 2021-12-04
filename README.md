# <img src="https://portalsam.net/wp-content/uploads/2021/10/IconHeart.png" width="64" height="64" /> MagicHealth

Minecraft Plugin that mimics the functionality of the Scaling Health mod *( or at least inspired by it )* adding new items including Heart Crystals that can permanently expand a player's health. Or completely limit a player's health all-together to less hearts.


  • Have a full twenty hearts:
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/HeartBarShowcase.png" width="366" height="109" />
  
  • A more challenging three hearts:
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/HealthBarSmall.png" width="366" height="85" />
  
  • Or even a ridiculous 100 hearts:
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/HealthBarLARGE.png" width="366" height="137" />
  
# Items

* **Heart Dust**

  **Heart Dust** is an item that will very rarely drop from many different hostile and passive mobs. It is the basis of most of the crafting for other items. On it's own it won't   do much of anything at all.
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/ItemIntroDust.png" width="763" height="202" />
  
* **Heart Shard**

  The **Heart Shard** is a crafted item that once again is another rare drop, it will always have a drop chance of 100% but only from boss mobs like the Wither or Ender Dragon.   It is used for crafting a **Heart Crystal**.
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/ItemIntroShard.png" width="763" height="211" />
  
* **Heart Crystal**

  The **Heart Crystal** is an extremely powerful item, when used by the player it will permanently expand their health by one heart *( or by how much is in the config.yml )*.     The player can not use a **Heart Crystal** if theyre already at the maximum health set in config.yml. 
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/ItemIntroCrystal.png" width="763" height="201" />
  
* **Heart Drain Amulet**

  If player with an expanded health would ever wish to *decrease their health* they can craft a **Heart Drain Amulet**. This item can permanently decrease a players health by     one heart. *( Or once again by how much is in the config.yml, and assuming the player has an expanded health to begin with. )*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/ItemIntroAmulet.png" width="763" height="242" />
  
# Crafting

* **Heart Shard Crafting**
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CraftingShard.png" width="479" height="273" />
  
* **Heart Crystal Crafting**
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CraftingCrystal.png" width="479" height="266" />
  
* **Heart Drain Amulet Crafting**
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CraftingAmulet.png" width="479" height="267" />
  
# Commands

Commands for MagicHealth, bare with me these commands get lengthy. They also all support TabComplete.

* **Magic Health Give Item**

  * This command can be supplied with the argument list to show the available items within the plugin, or the name of the item itself to give to yourself. A second number         argument can also be provided as the amount of that item.

  *Example:*
  ```
  /magichealthgive list
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CommandShowGive.png" width="763" height="45" />
  
  *Example:*
  ```
  /magichealthgive [yourname] heart_dust 5
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CommandResultGive.png" width="763" height="33" />
  
* **Magic Health Set Max**

  * This command can set the maximum health of yourself or a specified player, the second argument can either be a player name or a double, if it's a player name the 3rd           argument must also be a double.

  *Example:*
  ```
  /magichealthsetmax 20
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CommandShowSet.png" width="763" height="27" />
  
  *Example:*
  ```
  /magichealthsetmax portalsam 20
  ```
  *Result:*
  
  <img src="https://portalsam.net/wp-content/uploads/2021/10/CommandResultSet.png" width="763" height="27" />
  
# Configuration

  * All the drop rate percentages and amounts can be configured in config.yml, you can also change how much a **Heart Crystal** changes your max health or how much a **Heart       Drain Amulet** takes away. You can also change how many hearts a player starts out with or their maximum health they can get ever. There is also another data file               playerHealthData that holds UUID key-value pairs for how big a players health-bar is, you may change these manually if you so desire. As of Release-1.2 you can customize a       list of what mobs are in the common, uncommon, and boss mob categories so you can choose which mobs will drop these items, or if you'd like you can completely disable mob       drops all together. **Note:** to add a mob to the mob drop list they must be in the **EntityType** Enum *(Usually the Mob's name in capital letters with spaces separated in     underscores)*.
  

  <img src="https://portalsam.net/wp-content/uploads/2021/10/DebugShowConfig.png" width="765" height="380" />


# Permissions

  * magichealth.giveitem

    Access to the /magichealthgive command, default permission level: op.

  * magichealth.sethealth

    Access to the /magichealthsetmax command, default permission level: op.
    
    
