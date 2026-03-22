print('|'.ljust(10) +'\Calcular Média Aritimetica/')
Notas=[]
Totalnotas=0
for i in range (1,5):
    nota=float(input(f'🤔Informe sua Nota {i}: '))
    Notas.append(nota)
for nota in Notas:
    Totalnotas+=nota
Media=Totalnotas/4
print(f'Sua Média: {Media}')
if Media>=7:
    print('😎ATINGIU A MÉDIA NECESSARIA😎')
else:
    print('😱MÉDIA ABAIXO DA RECOMENDADA😱') 

    
