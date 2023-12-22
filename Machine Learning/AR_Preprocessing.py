import numpy as np
from tensorflow.keras.preprocessing import image
import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from keras.preprocessing.image import load_img
import matplotlib.pyplot as plt


# Load the trained model
saved_model_path=r'C:/Users/Abigail/Downloads/uji/model_AR.h5'
loaded_model = tf.keras.models.load_model(saved_model_path)

# Define the path to the image of eskrim you want to test
sample_image_path = 'C:/Users/Abigail/Downloads/uji/monas.jpg'  # Ganti dengan path sesuai lokasi gambar eskrim yang ingin diuji

# Load and preprocess the image
img = image.load_img(sample_image_path, target_size=(224, 224))
img_array = image.img_to_array(img)
img_array = np.expand_dims(img_array, axis=0)
img_array = tf.keras.applications.xception.preprocess_input(img_array)

# Make predictions
predictions = loaded_model.predict(img_array)

# Thresholding predictions (example: using 0.5 as the threshold)
threshold = 0.6
binary_prediction = (predictions > threshold).astype("int32")

# Print the raw predictions and binary prediction
print("Raw Predictions:", predictions)
print("Binary Prediction:", binary_prediction)

# Check if the prediction corresponds to Monas label
if binary_prediction == 1:
    print("Benar, ini Monas")
else:
    print("Ini bukan Monas")

# Display the image
plt.imshow(img)
plt.show()
