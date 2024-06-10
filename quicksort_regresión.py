import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pandas as pd

file_path = r'C:\Users\amans\OneDrive\Escritorio\resultados.csv'
data = pd.read_csv(file_path)

# Extraer los datos
n = data['Tamaño'].values
tiempo_secuencial = data['TiempoSecuencial'].values / 1e9  # Convertir nanosegundos a segundos
tiempo_concurrente = data['TiempoConcurrente'].values / 1e9  # Convertir nanosegundos a segundos

# Ajuste de regresión lineal
modelo_secuencial = LinearRegression()
modelo_secuencial.fit(n.reshape(-1, 1), tiempo_secuencial)

modelo_concurrente = LinearRegression()
modelo_concurrente.fit(n.reshape(-1, 1), tiempo_concurrente)

# Predicciones
pred_secuencial = modelo_secuencial.predict(n.reshape(-1, 1))
pred_concurrente = modelo_concurrente.predict(n.reshape(-1, 1))

# Gráfico
plt.figure(figsize=(10, 6))
plt.scatter(n, tiempo_secuencial, color='blue', label='Secuencial - Datos')
plt.plot(n, pred_secuencial, color='blue', linestyle='--', label='Secuencial - Regresión Lineal')
plt.scatter(n, tiempo_concurrente, color='red', label='Concurrente - Datos')
plt.plot(n, pred_concurrente, color='red', linestyle='--', label='Concurrente - Regresión Lineal')
plt.xlabel('Tamaño de entrada (n)')
plt.ylabel('Tiempo de ejecución (s)')
plt.legend()
plt.title('Comparación de Algoritmos de Ordenamiento')
plt.show()
