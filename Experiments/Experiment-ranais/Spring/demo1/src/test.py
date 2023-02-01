from colorsys import hsv_to_rgb
from PIL import Image

# Make some RGB values. 
# Cycle through hue vertically & saturation horizontally
colors = int(open("/home/ranais/Ranai/Fall21/309/3_mc_7/Experiments/Experiment-ranais/Spring/demo1/src/out").read().split())
# for hue in range(360):
#     for sat in range(100):
#         # Convert color from HSV to RGB
#         rgb = hsv_to_rgb(hue/360, sat/100, 1)
#         rgb = [int(0.5 + 255*u) for u in rgb]
#         colors.extend(rgb)

# Convert list to bytes
colors = bytes(colors)
img = Image.frombytes('RGB', (100, 360), colors)
img.show()
img.save('hues.png')
