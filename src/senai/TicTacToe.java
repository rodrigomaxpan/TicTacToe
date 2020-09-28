package senai;

import java.io.*;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.regex.*;

public class TicTacToe {

	public static Jogo jogo;
	private static int modoDeJogo;
	public static int count = 0;

	public static String escolhaUsuario;
	private static boolean opcaoValida;

	public static void main(String[] args) {

		System.out.println("\n\t+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_++_+_+_+_");
		System.out.println("\n\t Modos de jogar:");
		System.out.println("\n\t (1) Jogador x Computador");
		System.out.println("\n\t (2) Dois Jogadores");
		System.out.println("\n\t+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_++_+_+_+_");

		escolhaUsuario = getInput("\n\tEscolha o modo de jogar? (1/2): ");


		jogo = new Jogo();
		modoJogo(escolhaUsuario);


		Jogador[] jogadores = new Jogador[2];

		if (jogo.getModoDeJogo() == 1) {

			jogadores[0] = new Jogador("HUMANO");
			jogadores[1] = new Jogador("COMPUTADOR");

		} else {

			jogadores[0] = new Jogador("HUMANO");
			jogadores[1] = new Jogador("HUMANO");

		}

		System.out.println(jogo.output());

		while (!jogo.finalizou) {

			for (Jogador jogador : jogadores) {
				jogador.rodada();
				System.out.println("\n" + jogo.output());
				count += 1;

				if (jogo.finalizou) {
					break;
				}

			}
		}

		if (jogo.desenho) {

			System.out.println("\n\tO Jogo empatou!");

		} else {

			if (count % 2 == 1) {

				System.out.println("\n\tJogador X venceu!");

			} else {

				System.out.println("\n\tJogador O venceu!");

			}
		}
	}

	// Método para ler a opcão do modo do Jogo
	public static String getInput(String prompt) {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		System.out.print(prompt);
		System.out.flush();

		try {

			return stdin.readLine();

		} catch (Exception e) {

			return "Error: " + e.getMessage();

		}
	}

	// método para validar o modo do jogo que o usuário escolhey
	private static void modoJogo(String input) {

		opcaoValida = false;

		while (!opcaoValida) {
			if ((input.length() == 1) && (input.substring(0, 1).matches("[1-2]"))) {
				opcaoValida = true;
			} else {
				input = getInput("\n\tVocê precisa digitar '1' ou '2': ");
			}
		}

		jogo.setModoDeJogo(Integer.parseInt(input));

	}
}