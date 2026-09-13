# BetterEnd Crashed Ship Loot

[![Available on Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/betterend-crashed-ship-loot)
[![View on GuitHub](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/available/github_vector.svg)](https://github.com/MinenMaster/BetterEnd-Crashed-Ship-Loot)

A NeoForge 1.21.1 mod that puts loot in the crashed ships of
[BetterEnd: New Dawn](https://modrinth.com/mod/betterend-neoforge).

## What it does

BetterEnd scatters crashed ships across the End. They come furnished — brewing stand, potions,
dragon head, a purpur pedestal — but their chests generate completely empty. The chests are placed
without a loot table at all, so no datapack can fill them: there is nothing to override.

The ships are built from the vanilla `minecraft:end_city/ship` template. In vanilla, End City
chests are filled by the End City structure code when it handles the template's data markers;
nothing does that here, so the chest is placed with no loot table at all.

This mod hooks the ship's generation and gives every container inside the finished ship the loot
table `betterendcrashedshiploot:chests/crashed_ship`.

Only newly generated ships are affected. Ships in already-explored chunks stay as they are.

There is no elytra: the vanilla template holds it in an item frame, and BetterEnd places the ship
with `setIgnoreEntities(true)`, so the frame never appears.

## Configuration

`config/betterendcrashedshiploot-common.toml`:

| Option | Default | Meaning |
| --- | --- | --- |
| `enabled` | `true` | Fill crashed ship chests at all. |

## Changing the loot

The loot table is a normal datapack file. Override
`data/betterendcrashedshiploot/loot_table/chests/crashed_ship.json` in any datapack to change what
the ships contain.

## Requirements

- NeoForge 21.1.x
- BetterEnd: New Dawn

Compatible with [Lootr](https://modrinth.com/mod/lootr).

## Building

Requires a JDK 21.

```sh
./gradlew build
```

The third-party mods used for compilation and dev testing are pulled from Modrinth Maven; no jars
need to be downloaded by hand. Their pinned version ids live in `gradle.properties`.

To launch a dev client with BetterEnd and Lootr already present:

```sh
./gradlew runClient
```
