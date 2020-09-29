package senai;

public class Jogo {

	private int modoDeJogo = 0;
	private boolean finalizou = false;
	private boolean gradeCheia = false;
	private int tamanhoTabela;
	private Celulas[] tabela;

	public Jogo() {

		this.tamanhoTabela = 3;
		tabela = new Celulas[this.tamanhoTabela * this.tamanhoTabela];

		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new Celulas();
		}
	}

	public boolean isGradeCheia() {
		return gradeCheia;
	}

	public void setGradeCheia(boolean gradeCheia) {
		this.gradeCheia = gradeCheia;
	}

	public boolean isFinalizou() {
		return finalizou;
	}

	public void setFinalizou(boolean finalizou) {
		this.finalizou = finalizou;
	}

	public int getTamanhoTabela() {
		return this.tamanhoTabela;
	}

	public void setTamanhoTabela(int tamanhoTabela) {
		this.tamanhoTabela = tamanhoTabela;
	}

	public int getModoDeJogo() {
		return modoDeJogo;
	}

	public void setModoDeJogo(int modoDeJogo) {
		this.modoDeJogo = modoDeJogo;
	}

	// verifica se uma condição de vitória foi atendida e
	// envia o mapa do jogo atual para o console
	public String output() {
		verificaSeDeuTicTacToe();
		return desenhaGradePreenchida();
	}

	// coloca um X ou um O em uma célula no mapa do jogo
	public boolean setCell(int index) {

		if (tabela[index].vazio) {

			tabela[index].marcador();
			return true;

		} else {
			return false;
		}
	}


	private boolean verificaSeDeuTicTacToe() {

		boolean gradePreenchida;
		boolean linhaVencedora;
		boolean colunaVencedora;
		boolean giagonalVencedora;

		Celulas[][] rows = new Celulas[this.tamanhoTabela][this.tamanhoTabela];
		Celulas[][] columns = new Celulas[this.tamanhoTabela][this.tamanhoTabela];
		Celulas[][] diagonals = new Celulas[2][this.tamanhoTabela]; 

		// SE TODAS AS CELUAS ESTIVEREM PREENCHIDAS, FINAL DE JOGO
		gradePreenchida = true;
		for (int i = 0; i < this.tamanhoTabela * this.tamanhoTabela; i++) {

			if (tabela[i].vazio) {
				gradePreenchida = false;
			}
		}

		if (gradePreenchida) {
			finalizou = true;
			gradeCheia = true;
		}

		for (int i = 0; i < this.tamanhoTabela; i++) {
			for (int j = 0; j < this.tamanhoTabela; j++) {

				rows[i][j] = tabela[this.tamanhoTabela * i + j];
			}
		}

		for (int i = 0; i < this.tamanhoTabela; i++) {
			for (int j = 0; j < this.tamanhoTabela; j++) {

				columns[i][j] = tabela[i + this.tamanhoTabela * j];
			}
		}

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				for (int j = 0; j < this.tamanhoTabela; j++) {

					diagonals[i][j] = tabela[(this.tamanhoTabela + 1) * j];

				}
			} else {
				for (int j = 0; j < this.tamanhoTabela; j++) {

					diagonals[i][j] = tabela[(this.tamanhoTabela - 1) * (j + 1)];
				}
			}
		}


		// se uma linha tem todo o mesmo conteúdo e não está vazia
		// então o jogo acabou
		for (Celulas[] row : rows) {


			// se os elementos da linha forem todos iguais e não vazios
			// definir concluído como verdadeiro
			linhaVencedora = true;
			for (int i = 0; i < row.length - 1; i++) {
				if (row[i].output() != row[i + 1].output()) {

					linhaVencedora = false;
				}
				for (int j = 0; j < row.length - 1; j++) {
					if (row[i].vazio) {

						linhaVencedora = false;
					}
				}
			}

			if (linhaVencedora) {
				finalizou = true;
				gradeCheia = false;
			}
		}


		// se uma coluna tem todo o mesmo conteúdo e não está vazia
		// então o jogo acabou
		for (Celulas[] column : columns) {

			// se os elementos da coluna forem todos iguais e não vazios
			// definir concluído como verdadeiro
			colunaVencedora = true;
			for (int i = 0; i < column.length - 1; i++) {
				if (column[i].output() != column[i + 1].output()) {

					colunaVencedora = false;
				}

				for (int j = 0; j < column.length - 1; j++) {
					if (column[i].vazio) {

						colunaVencedora = false;
					}
				}
			}

			if (colunaVencedora) {
				finalizou = true;
				gradeCheia = false;
			}
		}


		// se uma diagonal tem todo o mesmo conteúdo e não está vazia
		// então o jogo acabou
		for (Celulas[] diagonal : diagonals) {

			// se os elementos diagonais são todos iguais e não estão vazios
			// definir concluído como verdadeiro
			giagonalVencedora = true;
			for (int i = 0; i < diagonal.length - 1; i++) {
				if (diagonal[i].output() != diagonal[i + 1].output()) {

					giagonalVencedora = false;
				}

				for (int j = 0; j < diagonal.length - 1; j++) {
					if (diagonal[i].vazio) {

						giagonalVencedora = false;
					}
				}
			}

			if (giagonalVencedora) {
				finalizou = true;
				gradeCheia = false;
			}
		}

		return finalizou;
	}


	private String desenhaGradePreenchida() {

		String top = "\t\t  ";
		String fill = "\t\t    ";
		String divider = "\t\t ---";
		String meat = "\t\t";
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String map = "\n";

		for (int i = 1; i < this.tamanhoTabela; i++) {

			top += i + "  ";

			if (i < 9) {
				top += " ";
			}

			fill += "|   ";
			divider += "+---";
		}

		top += this.tamanhoTabela + " \n";
		fill += "\n";
		divider += "\n";
		map += top + fill;

		for (int row = 1; row < 2; row++) {
			for (int column = 1; column < 2; column++) {

				meat += alphabet.substring(row - 1, row) + " " + tabela[3 * (row - 1) + (column - 1)].output();

				for (int i = 2; i < this.tamanhoTabela + 1; i++) {

					meat += " | " + tabela[3 * (row - 1) + (i - 1)].output();
				}
			}

			meat += "\n";
		}

		map += meat + fill;

		for (int row = 2; row < this.tamanhoTabela + 1; row++) {

			map += divider;
			map += fill;

			for (int column = 1; column < 2; column++) {

				meat = "\t\t" + alphabet.substring(row - 1, row) + " "
						+ tabela[this.tamanhoTabela * (row - 1) + (column - 1)].output();

				for (int i = column + 1; i < this.tamanhoTabela + 1; i++) {

					meat += " | " + tabela[this.tamanhoTabela * (row - 1) + (i - 1)].output();
				}
			}

			map += meat + "\n" + fill;
		}

		return map;
	}
}