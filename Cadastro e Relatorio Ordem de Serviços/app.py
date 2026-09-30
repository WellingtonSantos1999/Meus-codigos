from flask import Flask, render_template, request, redirect, url_for

# Inicializa o Flask
app = Flask(__name__)

# Lista para armazenar as Ordens de Serviço (Substituiria por um DB real)
ordem_servico = []
next_os_id = 1

# Classe Ordem de Serviço (Modelo de Dados)
class OrdemdeServiço:
    def __init__(self, id, nome, celular, model, defeito):
         self.id = id
         self.nome = nome
         self.celular = celular
         self.model = model
         self.defeito = defeito

    def __str__(self):
        return f"Cliente: {self.nome}, Celular: {self.celular}, Modelo: {self.model}, Defeito: {self.defeito}"

    # Adicionei validações simples de nome para o Flask
    # Em um app web, seria melhor usar Flask-WTF para validação de formulário
    @property
    def nome(self):
        return self._nome

    @nome.setter
    def nome(self, novo_nome):
        if not novo_nome.strip():
            self._nome = "Nome Invalido"
        elif any(char.isdigit() for char in novo_nome):
            self._nome = "Nome Invalido"
        else:
            self._nome = novo_nome

# --- Rotas da Aplicação (CRUD) ---

@app.route('/')
def index():
    """Rota principal: Exibe o menu (ou redireciona para o relatório)."""
    return render_template('index.html')

@app.route('/cadastrar', methods=['GET', 'POST'])
def cadastrar():
    """Cria uma nova OS (POST) ou exibe o formulário (GET)."""
    global next_os_id
    if request.method == 'POST':
        # Pega os dados do formulário
        nome = request.form.get('nome')
        celular_str = request.form.get('celular')
        model = request.form.get('model')
        defeito = request.form.get('defeito')
        
        try:
            celular = int(celular_str)
            ordem = OrdemdeServiço(next_os_id, nome, celular, model, defeito)
            
            # Garante que o nome não é "Invalido" devido ao setter
            if ordem.nome == "Nome Invalido":
                 return render_template('cadastrar.html', error="O Nome não pode ser vazio ou conter números.")

            ordem_servico.append(ordem)
            next_os_id += 1
            return redirect(url_for('relatorio')) # Redireciona após o sucesso
        except ValueError:
            return render_template('cadastrar.html', error="Digite um número de Celular válido.")
        except Exception as e:
            return render_template('cadastrar.html', error=f"Erro ao cadastrar: {e}")

    return render_template('cadastrar.html') # Exibe o formulário de cadastro (GET)

@app.route('/relatorio')
def relatorio():
    """Exibe a lista de todas as OSs (Read)."""
    return render_template('relatorio.html', ordens=ordem_servico)

@app.route('/editar/<int:os_id>', methods=['GET', 'POST'])
def editar(os_id):
    """Altera uma OS existente (Update)."""
    ordem_encontrada = next((os for os in ordem_servico if os.id == os_id), None)
    
    if not ordem_encontrada:
        return "Ordem de Serviço não encontrada", 404

    if request.method == 'POST':
        # Pega os dados do formulário de edição
        nome = request.form.get('nome')
        celular_str = request.form.get('celular')
        model = request.form.get('model')
        defeito = request.form.get('defeito')

        try:
            celular = int(celular_str)
            
            # Tenta atualizar o objeto
            ordem_encontrada.nome = nome
            ordem_encontrada.celular = celular
            ordem_encontrada.model = model
            ordem_encontrada.defeito = defeito

            # Verifica a validação do nome
            if ordem_encontrada.nome == "Nome Invalido":
                 return render_template('editar.html', ordem=ordem_encontrada, error="O Novo Nome não pode ser vazio ou conter números.")
            
            return redirect(url_for('relatorio'))
        except ValueError:
            return render_template('editar.html', ordem=ordem_encontrada, error="Digite um Novo Número de Celular válido.")

    # Exibe o formulário de edição pré-preenchido (GET)
    return render_template('editar.html', ordem=ordem_encontrada)

@app.route('/apagar/<int:os_id>', methods=['POST'])
def apagar(os_id):
    """Apaga uma OS existente (Delete)."""
    global ordem_servico
    ordem_servico = [os for os in ordem_servico if os.id != os_id]
    return redirect(url_for('relatorio')) # Redireciona de volta para o relatório

# Executa o aplicativo
if __name__ == '__main__':
    # Utilize debug=True apenas em desenvolvimento
    app.run(debug=True)