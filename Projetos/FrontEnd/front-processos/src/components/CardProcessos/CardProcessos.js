import { useState } from 'react'

import { Card, CardActions, CardContent, Typography, Grid, IconButton } from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete'
import EditIcon from '@material-ui/icons/Edit'
import ModalProcesso from '../../pages/ModalProcesso'

import { useStyles } from './CardProcessos.styles'

export const CardProcessos = ({ processo, onAtualizarProcessos }) => {
  const classes = useStyles()

  const [isModalVisible, setIsModalVisible] = useState(false)

  const fecharModalCadastro = (atualizarListagem) => {
    if (atualizarListagem) onAtualizarProcessos()
    setIsModalVisible(false)
  }

  return (
    <Card className={classes.root} elevation={2}>
      <CardContent>
        <Grid container justifyContent="space-between" className={classes.primeiraColuna}>
          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Nº Processo:</strong>
            </Typography>
            <Typography>{processo.nuProcesso}</Typography>
          </Grid>

          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Orgao/Setor:</strong>
            </Typography>
            <Typography>{processo.sgOrgaoSetor}</Typography>
          </Grid>

          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Ano</strong>
            </Typography>
            <Typography>{processo.nuAno}</Typography>
          </Grid>
        </Grid>
        <Grid container justifyContent="space-between">
          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Interessado:</strong>
            </Typography>
            <Typography>{processo.cdInteressado.nmInteressado}</Typography>
          </Grid>

          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Assunto:</strong>
            </Typography>
            <Typography>{processo.cdAssunto.descricao}</Typography>
          </Grid>
          <Grid item xs={4}>
            <Typography className={classes.title}>
              <strong>Descrição:</strong>
            </Typography>
            <Typography>{processo.descricao}</Typography>
          </Grid>
        </Grid>
      </CardContent>
      <CardActions>
        <Grid container justifyContent="flex-end">
          <IconButton
            className={classes.button}
            variant="contained"
            onClick={() => {
              setIsModalVisible(true)
            }}
          >
            <EditIcon />
          </IconButton>
          <IconButton className={classes.button} variant="contained">
            <DeleteIcon />
          </IconButton>

          <ModalProcesso open={isModalVisible} onClose={fecharModalCadastro} processo={processo}></ModalProcesso>
        </Grid>
      </CardActions>
    </Card>
  )
}
