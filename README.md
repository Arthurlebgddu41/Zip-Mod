# Zip Mod - Minecraft Compression System

A Minecraft Forge mod (version 1.20.1) that adds compression and decompression blocks for item storage.

## Features

- **Compressor Block**: Compress up to 3 items into a compressed ZIP item
- **Decompressor Block**: Decompress ZIP items back into their original items
- **GUI Interaction**: Right-click on blocks to open their interfaces
- **Item Preservation**: All original items are preserved during compression/decompression

## Installation

1. Ensure you have Minecraft 1.20.1 and Forge 47.2.0+ installed
2. Place the mod JAR file in your `mods` folder
3. Start Minecraft

## How to Use

### Compressor
1. Place the Compressor block in your world
2. Right-click to open the GUI
3. Place up to 3 items in the input slots
4. Click the "Compress" button
5. Your items will be compressed into a ZIP item in the output slot

### Decompressor
1. Place the Decompressor block in your world
2. Right-click to open the GUI
3. Place a compressed ZIP item in the input slot
4. Click the "Decompress" button
5. Your items will be extracted into the output slots

## Building

```bash
./gradlew build
```

The JAR file will be located in `build/libs/`

## Requirements

- Minecraft 1.20.1
- Forge 47.2.0+
- Java 17+

## License

MIT License - Feel free to use and modify as needed!
