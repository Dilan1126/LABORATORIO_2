/*
proposito: laboratorio 2 - Crear una aplicación en Java con GUI que permita responder un cuestionario de 5 preguntas, todas de selección
múltiple con única respuesta.
autor 1: Juan David Camargo - 2357800
autor 2: Dilan Garcia - 2357632
autor 3: Juan Diego Delgado - 2357745
fecha: 18/09/24
*/

package laboratorio_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LABORATORIO_2 extends JFrame {

    private final JLabel[] preguntas;
    private final JRadioButton[][] respuestas;
    private final ButtonGroup[] grupos;
    private final JButton botonCalificar, botonMostrarProgramadores;
    private final JLabel etiquetaResultado;

    // Respuestas correctas (índice de la respuesta correcta en cada grupo)
    private final int[] respuestasCorrectas = {0, 1, 2, 1, 0};

    public LABORATORIO_2() {
        setTitle("Cuestionario de Arte");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 1)); // Cambié a 8 filas para acomodar otro botón

        preguntas = new JLabel[5];
        respuestas = new JRadioButton[5][3];
        grupos = new ButtonGroup[5];

        // Estilos para las preguntas y respuestas
        Color[] coloresFondoPreguntas = {Color.CYAN, Color.LIGHT_GRAY, Color.PINK, Color.YELLOW, Color.ORANGE};
        Font fuentePregunta = new Font("Times New Roman", Font.BOLD, 14);

        String[][] opciones = {
            {"Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh"},
            {"Tipo de Dibujo", "Forma de arte que usa pigmentos sobre una superficie", "Tecnica de esculpir"},
            {"Acuarela", "Lienzo", "Marmol o piedra"},
            {"Martillo", "Pincel", "Cincel"},
            {"Crear figuras tridimensionales", "Hacer paisajes", "Hacer peliculas"}
        };

        Font fuenteRespuesta = new Font("Times New Roman", Font.PLAIN, 12);

        // Preguntas del cuestionario con sus estilos
        preguntas[0] = new JLabel("1. ¿Quién pintó la Mona Lisa?");
        preguntas[1] = new JLabel("2. ¿Qué es la pintura?");
        preguntas[2] = new JLabel("3. ¿Cuál es el material principal utilizado para hacer esculturas?");
        preguntas[3] = new JLabel("4. ¿Cuál es la principal herramienta del pintor para aplicar la pintura sobre el lienzo?");
        preguntas[4] = new JLabel("5. ¿En que se especializa un escultor?");

        // Crear los grupos de botones para cada pregunta
        for (int i = 0; i < 5; i++) {
            grupos[i] = new ButtonGroup();
            JPanel panelPregunta = new JPanel(new GridLayout(4, 1));

            // Aplicar estilos a las preguntas
            preguntas[i].setOpaque(true);
            preguntas[i].setBackground(coloresFondoPreguntas[i]); // Color de fondo de la pregunta
            preguntas[i].setFont(fuentePregunta); // Fuente de la pregunta
            panelPregunta.add(preguntas[i]);

            for (int j = 0; j < 3; j++) {
                respuestas[i][j] = new JRadioButton(opciones[i][j]);
                respuestas[i][j].setFont(fuenteRespuesta); // Fuente de las opciones
                panelPregunta.add(respuestas[i][j]);
                grupos[i].add(respuestas[i][j]);
            }
            add(panelPregunta);
        }

        // Botón para calcular el puntaje
        botonCalificar = new JButton("Calcular puntaje");
        botonCalificar.addActionListener((ActionEvent e) -> {
            calcularPuntaje();
        });

        // Botón para mostrar información de los programadores
        botonMostrarProgramadores = new JButton("Mostrar Programadores");
        botonMostrarProgramadores.addActionListener((ActionEvent e) -> {
            mostrarDatosProgramadores();
        });

        // Etiqueta para mostrar el resultado
        etiquetaResultado = new JLabel("Resultado: ", JLabel.CENTER);
        etiquetaResultado.setOpaque(true);
        etiquetaResultado.setFont(new Font("Arial", Font.PLAIN, 16));
        add(botonCalificar);
        add(botonMostrarProgramadores); // Agregar el botón a la ventana
        add(etiquetaResultado);
    }

    // Método para calcular el puntaje
    private void calcularPuntaje() {
        int puntaje = 0;

        // Verificar respuestas correctas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (respuestas[i][j].isSelected() && j == respuestasCorrectas[i]) {
                    puntaje++;
                }
            }
        }

        // Modificar el JLabel de resultado según el puntaje
        etiquetaResultado.setText("Resultado: " + puntaje + "/5");
        etiquetaResultado.setFont(new Font("Arial", Font.BOLD, 18)); // Tipo y tamaño de fuente siempre Bold 18

        if (puntaje == 0) {
            etiquetaResultado.setBackground(Color.RED);
            etiquetaResultado.setForeground(Color.WHITE);
        } else if (puntaje >= 1 && puntaje <= 2) {
            etiquetaResultado.setBackground(Color.ORANGE);
            etiquetaResultado.setForeground(Color.BLACK);
        } else if (puntaje >= 3 && puntaje <= 4) {
            etiquetaResultado.setBackground(Color.YELLOW);
            etiquetaResultado.setForeground(Color.BLACK);
        } else if (puntaje == 5) {
            etiquetaResultado.setBackground(Color.GREEN);
            etiquetaResultado.setForeground(Color.WHITE);
        }
    }

    // Método para mostrar los datos de los programadores
    private void mostrarDatosProgramadores() {
        JOptionPane.showMessageDialog(this, """
            2357800 - Juan David Camargo - juan.tabarez@correounivalle.edu.co
            2357745 - Juan Diego Delgado - juan.diego.delgado@correounivalle.edu.co
            2357632 - Dilan Garcia - dilan.garcia@correounivalle.edu.co
                                            """,
            "Información de los Programadores", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LABORATORIO_2().setVisible(true);
        });
    }
}
