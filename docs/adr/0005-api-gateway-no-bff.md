# 5. API Gateway no próprio `bff`, com Spring Cloud Gateway

- **Status:** aceita
- **Data:** 2026-11-05

**Decisão:** o `bff` é construído com Spring Cloud Gateway e acumula os papéis de API Gateway e BFF, como único ponto
de entrada do browser.

**Por quê:**

- Só há um tipo de cliente (o browser), então um gateway separado não teria trabalho próprio.
- O token relay junta no mesmo lugar o roteamento, a sessão e o OAuth.
- Spring Cloud Gateway, Spring Session e Spring Security fazem parte do mesmo ecossistema, o que dispensa aprender outra plataforma.

**Alternativa descartada:** gateway separado na frente do `bff` (Kong ou Spring Cloud Gateway): soma um 7º serviço e um
salto de rede sem um segundo tipo de cliente que justifique.

**Custo aceito:** o `bff` concentra duas responsabilidades e vira um ponto crítico da borda (réplicas stateless reduzem
o risco). Se surgir app mobile ou API para parceiros, será preciso extrair o gateway.
