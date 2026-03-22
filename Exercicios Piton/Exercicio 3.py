print('<--Conversão de Metros para centimetros ou Centimetros para metros-->')
print('Para metros utilize M/m e para Centimetros C/c')
Escolha=str(input('Converter Metros ou Centimetros?'))
if Escolha == 'M' or Escolha == 'm':
    Metros=float(input('Informe a quantidade Metros para fazer a Conversão: '))
    Centimetros=Metros*100 
    print(f'Metro convertido para centimetros: {Centimetros}cm')
elif Escolha== 'C' or Escolha== 'c':
    Centimetros=float(input('Informe a quantidade Centimetros para fazer a Conversão: '))
    Metros= Centimetros/100
    print(f'Metro convertido para centimetros: {Metros}m')
