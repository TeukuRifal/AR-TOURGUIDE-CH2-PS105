import os
import zipfile
import random
import shutil
import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from shutil import copyfile
import matplotlib.pyplot as plt
import pandas as pd

# Mount Google Drive in Colab for direct access to files
from google.colab import drive
drive.mount('/content/gdrive', force_remount=True)
drive_path = '/content/drive/MyDrive'

# Define paths for MONAS and BUKAN MONAS categories, and print the number of images in each path
source = '/content/gdrive/MyDrive/Capstone Project/DATASET'

source1= os.path.join(source, 'MONAS')
source2= os.path.join(source, 'BUKAN MONAS')

print(f"There are {len(os.listdir(source1))} images of MONAS.")
print(f"There are {len(os.listdir(source2))} images of BUKAN MONAS.")

# Create training, validation, and testing directories for MONAS and BUKAN MONAS categories in the specified root directory
root_directory = '/saving'

if os.path.exists(root_directory):
  shutil.rmtree(root_directory)

def create_train_val_dirs(root):
  """
  Creates directories for the train and test sets

  Args:
    root_path (string) - the base directory path to create subdirectories from

  Returns:
    None
  """
  os.makedirs(root)
  train_directory = os.path.join(root, 'training')
  val_directory = os.path.join(root, 'validation')
  test_directory = os.path.join(root, 'testing')

  train_directory_monas = os.path.join(train_directory, 'MONAS')
  val_directory_monas = os.path.join(val_directory, 'MONAS')
  test_directory_monas = os.path.join(test_directory, 'MONAS')

  train_directory_notmonas = os.path.join(train_directory, 'BUKAN MONAS')
  val_directory_notmonas = os.path.join(val_directory, 'BUKAN MONAS')
  test_directory_notmonas = os.path.join(test_directory, 'BUKAN MONAS')

  os.makedirs(train_directory_monas)
  os.makedirs(val_directory_monas)
  os.makedirs(test_directory_monas)

  os.makedirs(train_directory_notmonas)
  os.makedirs(val_directory_notmonas)
  os.makedirs(test_directory_notmonas)

try:
  create_train_val_dirs(root=root_directory)
except FileExistsError:
  print("You should not be seeing this since the upper directory is removed beforehand")

# Print the full paths of all subdirectories within the specified root directory using os.walk
for rootdirectory, direcs, files in os.walk(root_directory):
    for subdirecs in direcs:
        print(os.path.join(rootdirectory, subdirecs))

# Split data from SOURCE_DIR into TRAINING_DIR, VALIDATION_DIR, and TESTING_DIR based on specified SPLIT_SIZE and SPLIT_SIZE_TEST proportions
def split_data(SOURCE_DIR, TRAINING_DIR, VALIDATION_DIR, TESTING_DIR, SPLIT_SIZE, SPLIT_SIZE_TEST):
  """
  Splits the data into train and test sets

  Args:
    SOURCE_DIR (string): directory path containing the images
    TRAINING_DIR (string): directory path to be used for training
    VALIDATION_DIR (string): directory path to be used for validation
    TESTING_DIR (string): directory path to be used for testing
    SPLIT_SIZE (float): proportion of the dataset to be used for training
    SPLIT_SIZE_TEST (float): proportion of the dataset to be used for testing

  Returns:
    None
  """
  file_list = []


  for file in os.listdir(SOURCE_DIR):
    file_path = os.path.join(SOURCE_DIR,file)
    if(os.path.getsize(file_path) == 0):
      print(file + " is zero length, so ignoring.")
    else:
      file_list.append(file_path)

  trainnum_items = int(round(len(file_list) * SPLIT_SIZE, 0))
  train_list = random.sample(file_list, trainnum_items)
  remain_list = list(set(file_list) - set(train_list))
  testnum_items = int(round(len(remain_list) * SPLIT_SIZE_TEST/(1-SPLIT_SIZE), 0))
  test_list = random.sample(remain_list, testnum_items)
  valid_list = list(set(remain_list) - set(test_list))


  for f in train_list:
    copyfile(f,TRAINING_DIR+os.path.basename(f))
  for f in test_list:
    copyfile(f,TESTING_DIR +os.path.basename(f))
  for f in valid_list:
    copyfile(f,VALIDATION_DIR+os.path.basename(f))

  pass

# Organize and split data into training, validation, and testing sets for MONAS and BUKAN MONAS categories, and display the count of images in each directory
SOURCE_DIR_MONAS = source+"/MONAS/"
SOURCE_DIR_NOTMONAS = source+"/BUKAN MONAS/"

TRAINING_DIR = root_directory+"/training/"
VALIDATION_DIR = root_directory+"/validation/"
TESTING_DIR = root_directory+"/testing/"

TRAINING_MONAS_DIR = os.path.join(TRAINING_DIR, "MONAS/")
VALIDATION_MONAS_DIR = os.path.join(VALIDATION_DIR, "MONAS/")
TESTING_MONAS_DIR = os.path.join(TESTING_DIR, "MONAS/")

TRAINING_NOTMONAS_DIR = os.path.join(TRAINING_DIR, "BUKAN MONAS/")
VALIDATION_NOTMONAS_DIR = os.path.join(VALIDATION_DIR, "BUKAN MONAS/")
TESTING_NOTMONAS_DIR = os.path.join(TESTING_DIR, "BUKAN MONAS/")

if len(os.listdir(TRAINING_MONAS_DIR)) > 0:
  for file in os.scandir(TRAINING_MONAS_DIR):
    os.remove(file.path)
if len(os.listdir(VALIDATION_MONAS_DIR)) > 0:
  for file in os.scandir(VALIDATION_MONAS_DIR):
    os.remove(file.path)
if len(os.listdir(TESTING_MONAS_DIR)) > 0:
  for file in os.scandir(TESTING_MONAS_DIR):
    os.remove(file.path)

if len(os.listdir(TRAINING_NOTMONAS_DIR)) > 0:
  for file in os.scandir(TRAINING_NOTMONAS_DIR):
    os.remove(file.path)
if len(os.listdir(VALIDATION_NOTMONAS_DIR)) > 0:
  for file in os.scandir(VALIDATION_NOTMONAS_DIR):
    os.remove(file.path)
if len(os.listdir(TESTING_NOTMONAS_DIR)) > 0:
  for file in os.scandir(TESTING_NOTMONAS_DIR):
    os.remove(file.path)

split_size = 0.6
split_size_test = 0.15

split_data(SOURCE_DIR_MONAS, TRAINING_MONAS_DIR, VALIDATION_MONAS_DIR, TESTING_MONAS_DIR, split_size, split_size_test)
split_data(SOURCE_DIR_NOTMONAS, TRAINING_NOTMONAS_DIR, VALIDATION_NOTMONAS_DIR, TESTING_NOTMONAS_DIR, split_size, split_size_test)

print(f"\n\nOriginal MONAS's directory has {len(os.listdir(SOURCE_DIR_MONAS))} images")
print(f"\n\nOriginal MONAS's directory has {len(os.listdir(SOURCE_DIR_NOTMONAS))} images")

print(f"There are {len(os.listdir(TRAINING_MONAS_DIR))} images of 0 for training")
print(f"There are {len(os.listdir(VALIDATION_MONAS_DIR))} images of 0 for validation")
print(f"There are {len(os.listdir(TESTING_MONAS_DIR))} images of 0 for testing")

print(f"There are {len(os.listdir(TRAINING_NOTMONAS_DIR))} images of 0 for training")
print(f"There are {len(os.listdir(VALIDATION_NOTMONAS_DIR))} images of 0 for validation")
print(f"There are {len(os.listdir(TESTING_NOTMONAS_DIR))} images of 0 for testing")

# Create training and validation data generators using ImageDataGenerator for image augmentation and normalization
def train_val_generators(TRAINING_DIR, VALIDATION_DIR):
  """
  Creates the training and validation data generators

  Args:
    TRAINING_DIR (string): directory path containing the training images
    VALIDATION_DIR (string): directory path containing the testing/validation images

  Returns:
    train_generator, validation_generator - tuple containing the generators
  """
  train_datagen = ImageDataGenerator( rescale = 1.0/255., rotation_range=10, width_shift_range=0.2,
                                     height_shift_range=0.2,shear_range=0.1, zoom_range=0.3,
                                      horizontal_flip=False, fill_mode='nearest')

  train_generator = train_datagen.flow_from_directory(directory=TRAINING_DIR,
                                                      batch_size=32,
                                                      class_mode='binary',
                                                      target_size=(224,224))

  validation_datagen = ImageDataGenerator( rescale = 1.0/255. )

  validation_generator = validation_datagen.flow_from_directory(directory=VALIDATION_DIR,
                                                                batch_size=32,
                                                                class_mode='binary',
                                                                target_size=(224,224))

  return train_generator, validation_generator

# Get training and validation data generators using the train_val_generators function
train_generator, validation_generator = train_val_generators(TRAINING_DIR, VALIDATION_DIR)

# Set preprocessing function for Xception model in TensorFlow/Keras
preprocessing = tf.keras.applications.xception.preprocess_input

# Create an Xception base model with specified input shape, excluding the top layer, and using pre-trained ImageNet weights
IMG_SHAPE = (224,224) + (3,)
base_model = tf.keras.applications.Xception(input_shape=IMG_SHAPE,
                                               include_top=False,
                                               weights='imagenet')

# Generate a batch of images and labels from the training generator, pass through the Xception base model, and print the shape of the resulting feature batch
image_batch, label_batch = next(iter(train_generator))
fea_batch = base_model(image_batch)
print(fea_batch.shape)

# Set the Xception base model to be non-trainable
base_model.trainable = False

# Print a summary of the Xception base model architecture
base_model.summary()

# Apply Global Average Pooling 2D to the feature batch obtained from the Xception base model and print the shape of the resulting averaged features
global_average_layer = tf.keras.layers.GlobalAveragePooling2D()
fea_batch_average = global_average_layer(fea_batch)
print(fea_batch_average.shape)

# Create a Dense layer with 1 neuron and 'sigmoid' activation, apply it to the averaged feature batch, and print the shape of the resulting predictions
prediction_layer = tf.keras.layers.Dense(1, activation = 'sigmoid')
prediction_batch = prediction_layer(fea_batch_average)
print(prediction_batch.shape)

# Create a new model with specified input shape, preprocessing, Xception base model, global average pooling, dropout, and a dense prediction layer
inputs = tf.keras.Input(shape=(224, 224, 3))
x = preprocessing(inputs)
x = base_model(x, training=False)
x = global_average_layer(x)
x = tf.keras.layers.Dropout(0.1)(x)
outputs = prediction_layer(x)
model = tf.keras.Model(inputs, outputs)

# Compile the model with Nadam optimizer, Binary Crossentropy loss, and Binary Accuracy metric
base_learning_rate = 0.0001
model.compile(optimizer='nadam',
              loss=tf.keras.losses.BinaryCrossentropy(from_logits=True),
              metrics=[tf.keras.metrics.BinaryAccuracy(threshold=0.5, name='accuracy')])

# Train the model on the specified number of epochs using the training and validation generators
history = model.fit(train_generator,
                    epochs=75,
                    validation_data=validation_generator)

# Create a testing data generator and make predictions using the trained model, then evaluate the model on the testing generator
testing_datagen = ImageDataGenerator( rescale = 1.0/255. )
testing_generator = testing_datagen.flow_from_directory(directory=TESTING_DIR,
                                                      batch_size=32,
                                                      class_mode='binary',
                                                      target_size=(224,224))

predictions = model.predict(testing_generator)
threshold = 0.5
binary_predictions = (predictions > threshold).astype("int32")
results = model.evaluate(testing_generator, batch_size=32)
print("test loss, test acc:", results)

# Print a summary of the entire model architecture, including the Xception base model and the added layers
model.summary()

# Saving the trained model as a Keras H5 file.
saved_model_path = "./model_AR.h5"
model.save(saved_model_path)

# Model Pre-processing
import cv2
import numpy as np
from tensorflow import keras

# Load the saved model and make predictions on a sample image
import numpy as np
from tensorflow.keras.preprocessing import image

loaded_model = tf.keras.models.load_model(saved_model_path)

sample_image_path = '/content/monas.jpg'

img = image.load_img(sample_image_path, target_size=(224, 224))
img_array = image.img_to_array(img)
img_array = np.expand_dims(img_array, axis=0)
img_array = tf.keras.applications.xception.preprocess_input(img_array)

predictions = loaded_model.predict(img_array)

threshold = 0.6
binary_prediction = (predictions > threshold).astype("int32")

print("Raw Predictions:", predictions)
print("Binary Prediction:", binary_prediction)

if binary_prediction == 1:
    print("Benar, ini Monas")
else:
    print("Ini bukan Monas")

plt.imshow(img)
plt.show()